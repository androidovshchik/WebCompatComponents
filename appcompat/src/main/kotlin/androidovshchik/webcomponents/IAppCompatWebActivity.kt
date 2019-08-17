/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.webcomponents

import androidovshchik.webcomponents.models.WebEngine

interface IAppCompatWebActivity {

    val engine: WebEngine

    var isReady: Boolean

    fun onReadyEvent()

    fun onFatalError()
}
