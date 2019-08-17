/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents.models

import android.graphics.Bitmap
import androidovshchik.webcomponents.BLANK_PAGE

@Suppress("unused")
open class WebPage {

    var url = BLANK_PAGE

    var code = 0

    var icon: Bitmap? = null

    var title = ""

    var description = ""

    constructor()

    constructor(url: String, code: Int = 0, icon: Bitmap? = null, title: String = "", description: String = "") {
        this.url = url
        this.code = code
        this.icon = icon?.copy(icon.config, true)
        this.title = title
        this.description = description
    }

    open fun release() {
        try {
            icon?.recycle()
        } catch (e: Throwable) {
        }
    }
}