/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.webcomponents

interface IWebCompatLayout : IWebCompatView {

    var enableSwipeLayout: Boolean

    var enableProgressBar: Boolean

    var accentColor: Int
}
