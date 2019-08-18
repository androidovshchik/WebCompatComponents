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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseWebCompatActivity : AppCompatActivity(), IWebCompatActivity {

    abstract var webLayout: BaseWebCompatLayout

    override var isReady = true

    override fun onPostCreate(savedInstanceState: Bundle?) {
        if (intent.getBooleanExtra(EXTRA_ARROW_BACK, true)) {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }
        webLayout.apply {
            if (intent.hasExtra(EXTRA_SWIPE_LAYOUT)) {
                enableSwipeLayout = intent.getBooleanExtra(EXTRA_SWIPE_LAYOUT, true)
            }
            if (intent.hasExtra(EXTRA_PROGRESS_BAR)) {
                enableProgressBar = intent.getBooleanExtra(EXTRA_PROGRESS_BAR, true)
            }
            if (intent.hasExtra(EXTRA_ACCENT_COLOR)) {
                // todo accent color
                accentColor = intent.getIntExtra(EXTRA_ACCENT_COLOR, 0)
            }
            if (intent.getBooleanExtra(EXTRA_DYNAMIC_TITLE, false)) {
                addWebViewListener {
                    onPageFinished {
                        title = it.title
                    }
                }
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
        if (!isFinishing) {
            webLayout.load(intent.getStringExtra(EXTRA_INPUT_DATA))
        }
    }

    override fun onFatalError() {
        if (!isFinishing) {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (intent.getBooleanExtra(EXTRA_OPTIONS_MENU, true)) {
            menuInflater.inflate(R.menu.web_menu, menu)
            return super.onCreateOptionsMenu(menu)
        }
        return false
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        intent.run {
            menu.findItem(R.id.action_reload_page).isVisible = getBooleanExtra(EXTRA_MENU_RELOAD, true)
            menu.findItem(R.id.action_copy_link).isVisible = getBooleanExtra(EXTRA_MENU_COPY, true)
            menu.findItem(R.id.action_share_link).isVisible = getBooleanExtra(EXTRA_MENU_SHARE, true)
            menu.findItem(R.id.action_open_browser).isVisible = getBooleanExtra(EXTRA_MENU_BROWSER, true)
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
                    .setChooserTitle(R.string.web_compat_share_via)
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

        const val EXTRA_INPUT_DATA = "web_extra_input_data"
        const val EXTRA_DYNAMIC_TITLE = "web_extra_dynamic_title"
        const val EXTRA_ARROW_BACK = "web_extra_arrow_back"
        const val EXTRA_NAVIGATE_BACK = "web_extra_navigate_back"
        const val EXTRA_OPTIONS_MENU = "web_extra_options_menu"
        const val EXTRA_MENU_RELOAD = "web_extra_menu_reload"
        const val EXTRA_MENU_COPY = "web_extra_menu_copy"
        const val EXTRA_MENU_SHARE = "web_extra_menu_share"
        const val EXTRA_MENU_BROWSER = "web_extra_menu_browser"
        const val EXTRA_SWIPE_LAYOUT = "web_extra_swipe_layout"
        const val EXTRA_PROGRESS_BAR = "web_extra_progress_bar"
        const val EXTRA_ACCENT_COLOR = "web_extra_accent_color"
    }
}
