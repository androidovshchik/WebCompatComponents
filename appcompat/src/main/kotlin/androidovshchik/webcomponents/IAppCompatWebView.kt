/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.webcomponents

import androidovshchik.webcomponents.models.WebEngine
import androidovshchik.webcomponents.models.WebPage

interface IAppCompatWebView {

    val engine: WebEngine

    val page: WebPage

    val history: ArrayList<out WebPage>

    var listener: IWebViewListener?

    /**
     * Consider that it should be called at [android.app.Activity.onPostResume]
     */
    fun onResume()

    fun loadUrl(url: String?)

    fun reload()

    fun navigateBack(): Boolean

    fun navigateForward(): Boolean

    fun clearHistory()

    fun onPause()

    fun onDestroy()
}
