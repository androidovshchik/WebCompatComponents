/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.webcomponents

import androidovshchik.webcomponents.models.WebPage

interface IWebViewListener {

    fun shouldOverrideLoading(page: WebPage): Boolean

    fun onPageStarted(page: WebPage)

    fun onPageFinished(page: WebPage)

    fun onPageError(page: WebPage)
}
