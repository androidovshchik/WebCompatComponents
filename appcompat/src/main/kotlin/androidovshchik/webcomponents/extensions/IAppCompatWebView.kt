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

fun IAppCompatWebView.setWebViewListener(init: IWebViewListener.() -> Unit) {
    listener = WebViewHelper().apply(init)
}

inline val IAppCompatWebView.isUIThread: Boolean
    get() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            println("This method must be called on the UI thread")
            return false
        }
        return true
    }