/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.webcomponents

import androidovshchik.webcomponents.models.WebBrowser
import androidovshchik.webcomponents.models.WebPage
import androidovshchik.webcomponents.models.WebProxy

interface IWebCompatView {

    val browser: WebBrowser

    var page: WebPage

    val history: ArrayList<out WebPage>

    var proxy: WebProxy?

    var userAgent: String

    val listeners: HashSet<IWebViewListener>

    /**
     * Consider that it should be called at [android.app.Activity.onPostResume]
     */
    fun onResume()

    fun get(url: CharSequence?, headers: Map<String, String>): String?

    fun post(url: CharSequence?, headers: Map<String, String>): String?

    fun load(data: CharSequence?, baseHref: CharSequence? = null)

    fun reload()

    fun navigate(steps: Int): Boolean

    fun onPause()

    fun onDestroy()
}
