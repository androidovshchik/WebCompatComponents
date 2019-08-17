/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.webcomponents

import android.graphics.Bitmap

interface IWebViewListener {

    fun onPageStarted(url: String, favicon: Bitmap?)

    fun onPageFinished(url: String)

    fun onPageError(url: String, code: Int, description: String)
}
