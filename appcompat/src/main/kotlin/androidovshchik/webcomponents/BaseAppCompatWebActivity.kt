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
import androidovshchik.webcomponents.extensions.addWebViewListener
import androidovshchik.webcomponents.extensions.openBrowser
import androidovshchik.webcomponents.extensions.showToast
import androidovshchik.webcomponents.models.WebPage
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseAppCompatWebActivity : AppCompatActivity(), IAppCompatWebActivity {

    abstract var webLayout: BaseAppCompatWebLayout

    override var isReady = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.getBooleanExtra(EXTRA_DYNAMIC_TITLE, false)) {
            webLayout.addWebViewListener {
                onPageFinished {
                    title = it.title
                }
            }
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        if (intent.getBooleanExtra(EXTRA_ARROW_BACK, true)) {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }
        super.onPostCreate(savedInstanceState)
    }

    override fun onPostResume() {
        if (isReady) {
            webLayout.onResume()
        }
        super.onPostResume()
    }

    override fun onReadyEvent() {
        webLayout.load(intent.getStringExtra(EXTRA_INPUT_DATA) ?: WebPage.BLANK_PAGE)
    }

    override fun onFatalError() {
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (intent.getBooleanExtra(EXTRA_OPTIONS_MENU, true)) {
            menuInflater.inflate(R.menu.web_activity_menu, menu)
            return super.onCreateOptionsMenu(menu)
        }
        return false
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.run {
            findItem(R.id.action_reload_page).isVisible = intent.getBooleanExtra(EXTRA_MENU_RELOAD, true)
            findItem(R.id.action_copy_link).isVisible = intent.getBooleanExtra(EXTRA_MENU_COPY, true)
            findItem(R.id.action_share_link).isVisible = intent.getBooleanExtra(EXTRA_MENU_SHARE, true)
            findItem(R.id.action_open_browser).isVisible = intent.getBooleanExtra(EXTRA_MENU_BROWSER, true)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.action_reload_page -> {
                if (isReady) {
                    webLayout.reload()
                }
            }
            R.id.action_copy_link -> {
                (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).run {
                    primaryClip = ClipData.newPlainText("", webLayout.page.url)
                }
            }
            R.id.action_share_link -> {
                ShareCompat.IntentBuilder.from(this)
                    .setChooserTitle(R.string.web_activity_action_share_via)
                    .setType("text/plain")
                    .setText(webLayout.page.url)
                    .startChooser()
            }
            R.id.action_open_browser -> {
                openBrowser(webLayout.page.url)?.let {
                    showToast(it.message)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (!intent.getBooleanExtra(EXTRA_NAVIGATE_BACK, true) || !isReady || !webLayout.navigate(-1)) {
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

    companion object {

        const val EXTRA_INPUT_DATA = "input_data"

        const val EXTRA_DYNAMIC_TITLE = "dynamic_title"

        const val EXTRA_ARROW_BACK = "arrow_back"

        const val EXTRA_NAVIGATE_BACK = "navigate_back"

        const val EXTRA_OPTIONS_MENU = "options_menu"

        const val EXTRA_MENU_RELOAD = "menu_reload"

        const val EXTRA_MENU_COPY = "menu_copy"

        const val EXTRA_MENU_SHARE = "menu_share"

        const val EXTRA_MENU_BROWSER = "menu_browser"

        const val EXTRA_SWIPE_LAYOUT = "swipe_layout"

        const val EXTRA_PROGRESS_BAR = "progress_bar"

        const val EXTRA_ACCENT_COLOR = "accent_color"
    }
}
