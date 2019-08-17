/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.webcomponents.extensions

import android.os.Looper
import androidovshchik.webcomponents.IAppCompatWebView
import androidovshchik.webcomponents.IWebViewListener
import androidovshchik.webcomponents.WebViewHelper
import androidovshchik.webcomponents.exceptions.InvalidThreadException

fun IAppCompatWebView.setWebViewListener(init: IWebViewListener.() -> Unit) {
    listener = WebViewHelper().apply(init)
}

fun IAppCompatWebView.checkThread() {
    if (Looper.myLooper() != Looper.getMainLooper()) {
        throw InvalidThreadException()
    }
}