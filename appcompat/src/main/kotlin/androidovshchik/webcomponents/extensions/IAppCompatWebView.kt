/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.webcomponents.extensions

import android.os.Looper
import androidovshchik.webcomponents.IAppCompatWebView
import androidovshchik.webcomponents.IWebViewListener
import androidovshchik.webcomponents.WebViewListener

fun IAppCompatWebView.addWebViewListener(init: WebViewListener.() -> Unit): IWebViewListener {
    return WebViewListener().apply {
        init()
        listeners.add(this)
    }
}

inline val IAppCompatWebView.isUIThread: Boolean
    get() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            println("This method should be called on the UI thread")
            return false
        }
        return true
    }