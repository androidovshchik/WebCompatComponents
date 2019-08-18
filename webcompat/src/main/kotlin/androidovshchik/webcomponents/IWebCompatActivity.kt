/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.webcomponents

import androidovshchik.webcomponents.models.WebBrowser

interface IWebCompatActivity {

    val browser: WebBrowser

    var isReady: Boolean

    fun onReadyEvent()

    fun onFatalError()
}
