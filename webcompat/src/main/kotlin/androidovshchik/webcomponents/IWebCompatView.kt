/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.webcomponents

import androidovshchik.webcomponents.models.Batch
import androidovshchik.webcomponents.models.WebBrowser
import androidovshchik.webcomponents.models.WebPage

interface IWebCompatView {

    val browser: WebBrowser

    val page: WebPage

    val history: ArrayList<out WebPage>

    val listeners: HashSet<IWebViewListener>

    /**
     * Consider that it should be called at [android.app.Activity.onPostResume]
     */
    fun onResume()

    fun load(input: Batch)

    fun reload()

    fun navigate(steps: Int): Boolean

    fun onPause()

    fun onDestroy()
}
