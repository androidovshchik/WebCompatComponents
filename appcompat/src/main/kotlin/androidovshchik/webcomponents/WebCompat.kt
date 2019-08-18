/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.webcomponents

import android.content.Context
import android.content.Intent
import androidovshchik.webcomponents.extensions.tryStartActivity

class WebCompat {

    @Suppress("MemberVisibilityCanBePrivate")
    open class IntentBuilder(private val context: Context) {

        val intent = Intent(context, Class.forName("${javaClass.`package`}.AppCompatWebActivity"))

        open fun inputData(data: String) = apply {
            intent.putExtra(BaseAppCompatWebActivity.EXTRA_INPUT_DATA, data)
        }

        open fun arrowBack(enable: Boolean) = apply {
            intent.putExtra(BaseAppCompatWebActivity.EXTRA_ARROW_BACK, enable)
        }

        open fun optionsMenu(enable: Boolean) = apply {
            intent.putExtra(BaseAppCompatWebActivity.EXTRA_OPTIONS_MENU, enable)
        }

        open fun menuReload(enable: Boolean) = apply {
            intent.putExtra(BaseAppCompatWebActivity.EXTRA_MENU_RELOAD, enable)
        }

        open fun menuCopy(enable: Boolean) = apply {
            intent.putExtra(BaseAppCompatWebActivity.EXTRA_MENU_COPY, enable)
        }

        open fun menuShare(enable: Boolean) = apply {
            intent.putExtra(BaseAppCompatWebActivity.EXTRA_MENU_SHARE, enable)
        }

        open fun menuBrowser(enable: Boolean) = apply {
            intent.putExtra(BaseAppCompatWebActivity.EXTRA_MENU_BROWSER, enable)
        }

        open fun swipeLayout(enable: Boolean) = apply {
            intent.putExtra(BaseAppCompatWebActivity.EXTRA_SWIPE_LAYOUT, enable)
        }

        open fun progressBar(enable: Boolean) = apply {
            intent.putExtra(BaseAppCompatWebActivity.EXTRA_PROGRESS_BAR, enable)
        }

        open fun startActivity() {
            context.tryStartActivity(intent)
        }
    }
}