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

    val listeners: HashSet<IWebViewListener>

    /**
     * Consider that it should be called at [android.app.Activity.onPostResume]
     */
    fun onResume()

    fun load(data: CharSequence?)

    fun reload()

    fun navigate(steps: Int): Boolean

    fun onPause()

    fun onDestroy()
}
