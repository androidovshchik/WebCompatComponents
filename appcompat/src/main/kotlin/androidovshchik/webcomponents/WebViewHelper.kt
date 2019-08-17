/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.webcomponents

import androidovshchik.webcomponents.models.WebPage

private typealias OnPageStarted = (page: WebPage) -> Unit

private typealias OnPageFinished = (page: WebPage) -> Unit

private typealias OnPageError = (page: WebPage) -> Unit

@Suppress("MemberVisibilityCanBePrivate")
open class WebViewHelper : IWebViewListener {

    protected var pageStarted: OnPageStarted? = null

    protected var pageFinished: OnPageFinished? = null

    protected var pageError: OnPageError? = null

    open fun onPageStarted(onPageStarted: OnPageStarted) {
        pageStarted = onPageStarted
    }

    open fun onPageFinished(onPageFinished: OnPageFinished) {
        pageFinished = onPageFinished
    }

    open fun onPageError(onPageError: OnPageError) {
        pageError = onPageError
    }

    override fun onPageStarted(page: WebPage) {
        pageStarted?.invoke(page)
    }

    override fun onPageFinished(page: WebPage) {
        pageFinished?.invoke(page)
    }

    override fun onPageError(page: WebPage) {
        pageError?.invoke(page)
    }
}