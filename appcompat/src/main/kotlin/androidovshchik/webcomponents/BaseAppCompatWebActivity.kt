/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidovshchik.webcomponents.appcompat.R
import androidovshchik.webcomponents.extensions.openBrowser
import androidovshchik.webcomponents.extensions.showToast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseAppCompatWebActivity : AppCompatActivity(), IAppCompatWebActivity {

    abstract var webLayout: BaseAppCompatWebLayout

    override var isReady = true

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onPostResume() {
        super.onPostResume()
        if (isReady) {
            webLayout.onResume()
        }
    }

    override fun onReady() {
        webLayout.loadUrl(intent.getStringExtra(Constant.EXTRA_URL) ?: Constant.BLANK_PAGE)
    }

    override fun onFailure() {
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.web_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.action_refresh_page -> {
                if (isReady) {
                    webLayout.reload()
                }
            }
            R.id.action_copy_link -> {
                val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboardManager.primaryClip = ClipData.newPlainText("", webLayout.page.url)
            }
            R.id.action_share_link -> {
                startActivity(ShareCompat.IntentBuilder.from(this)
                    .setChooserTitle(R.string.web_activity_action_share_via)
                    .setType("text/plain")
                    .setText(webLayout.page.url)
                    .createChooserIntent())
            }
            R.id.action_open_browser -> {
                openBrowser(webLayout.page.url)?.let {
                    showToast(it.message)
                }
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onBackPressed() {
        if (!isReady || !webLayout.navigateBack()) {
            super.onBackPressed()
        }
    }

    override fun onPause() {
        if (isReady) {
            webLayout.onPause()
        }
        super.onPause()
    }

    override fun onDestroy() {
        webLayout.onDestroy()
        super.onDestroy()
    }
}
