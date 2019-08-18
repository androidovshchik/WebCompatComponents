/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.webcomponents

import android.content.Context
import android.content.Intent
import androidovshchik.webcomponents.extensions.tryStartActivity
import androidovshchik.webcomponents.models.WebRequest

class WebCompat {

    @Suppress("MemberVisibilityCanBePrivate")
    open class IntentBuilder(context: Context) {

        val intent = Intent(context, Class.forName("${javaClass.`package`}.WebCompatActivity"))

        open fun requests(vararg requests: WebRequest) = apply {
            intent.putExtra(BaseWebCompatActivity.EXTRA_INPUT_DATA, requests)
        }

        open fun pageTitle(enable: Boolean) = apply {
            intent.putExtra(BaseWebCompatActivity.EXTRA_DYNAMIC_TITLE, enable)
        }

        open fun arrowBack(enable: Boolean) = apply {
            intent.putExtra(BaseWebCompatActivity.EXTRA_ARROW_BACK, enable)
        }

        open fun navigateBack(enable: Boolean) = apply {
            intent.putExtra(BaseWebCompatActivity.EXTRA_NAVIGATE_BACK, enable)
        }

        open fun optionsMenu(enable: Boolean) = apply {
            intent.putExtra(BaseWebCompatActivity.EXTRA_OPTIONS_MENU, enable)
        }

        open fun menuReload(enable: Boolean) = apply {
            intent.putExtra(BaseWebCompatActivity.EXTRA_MENU_RELOAD, enable)
        }

        open fun menuCopy(enable: Boolean) = apply {
            intent.putExtra(BaseWebCompatActivity.EXTRA_MENU_COPY, enable)
        }

        open fun menuShare(enable: Boolean) = apply {
            intent.putExtra(BaseWebCompatActivity.EXTRA_MENU_SHARE, enable)
        }

        open fun menuBrowser(enable: Boolean) = apply {
            intent.putExtra(BaseWebCompatActivity.EXTRA_MENU_BROWSER, enable)
        }

        open fun swipeLayout(enable: Boolean) = apply {
            intent.putExtra(BaseWebCompatActivity.EXTRA_SWIPE_LAYOUT, enable)
        }

        open fun progressBar(enable: Boolean) = apply {
            intent.putExtra(BaseWebCompatActivity.EXTRA_PROGRESS_BAR, enable)
        }

        open fun accentColor(color: Int) = apply {
            intent.putExtra(BaseWebCompatActivity.EXTRA_ACCENT_COLOR, color)
        }

        open fun startActivity(context: Context?) = context?.tryStartActivity(intent)
    }
}