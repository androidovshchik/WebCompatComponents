/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents.models

import android.graphics.Bitmap
import androidovshchik.webcomponents.Constant

@Suppress("unused")
open class WebPage {

    var url = Constant.BLANK_PAGE

    var icon: Bitmap? = null

    var title = url

    var description = ""

    constructor()

    constructor(url: String, icon: Bitmap? = null, title: String = url, description: String = "") {
        this.url = url
        this.icon = icon
        this.title = title
        this.description = description
    }
}