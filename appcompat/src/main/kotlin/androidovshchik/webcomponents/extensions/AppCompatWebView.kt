/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.webcomponents.extensions

import android.graphics.Bitmap
import android.os.Looper
import androidovshchik.webcomponents.IAppCompatWebView
import androidovshchik.webcomponents.IWebViewListener
import androidovshchik.webcomponents.exceptions.InvalidThreadException

private typealias OnPageStarted = (url: String, favicon: Bitmap?) -> Unit

private typealias OnPageFinished = (url: String) -> Unit

private typealias OnPageError = (url: String, code: Int, description: String) -> Unit

fun IAppCompatWebView.checkThread() {
    if (Looper.myLooper() != Looper.getMainLooper()) {
        throw InvalidThreadException()
    }
}

fun IAppCompatWebView.setWebViewListener(init: IWebViewListener.() -> Unit) {
    listener = AppCompatWebViewHelper().apply(init)
}

open class AppCompatWebViewHelper : IWebViewListener {

    private var pageStarted: OnPageStarted? = null

    private var pageFinished: OnPageFinished? = null

    private var pageError: OnPageError? = null

    fun onPageStarted(onPageStarted: OnPageStarted) {
        pageStarted = onPageStarted
    }

    fun onPageFinished(onPageFinished: OnPageFinished) {
        pageFinished = onPageFinished
    }

    fun onPageError(onPageError: OnPageError) {
        pageError = onPageError
    }

    override fun onPageStarted(url: String, favicon: Bitmap?) {
        pageStarted?.invoke(url, favicon)
    }

    override fun onPageFinished(url: String) {
        pageFinished?.invoke(url)
    }

    override fun onPageError(url: String, code: Int, description: String) {
        pageError?.invoke(url, code, description)
    }
}