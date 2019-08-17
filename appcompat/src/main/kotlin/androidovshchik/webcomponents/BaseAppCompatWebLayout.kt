/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidovshchik.webcomponents.extensions.checkThread
import androidovshchik.webcomponents.models.WebPage
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@Suppress("LeakingThis", "MemberVisibilityCanBePrivate")
abstract class BaseAppCompatWebLayout : FrameLayout, IAppCompatWebView {

    abstract var webView: IAppCompatWebView

    val swipeRefreshLayout = SwipeRefreshLayout(context).apply {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    val frameLayout = FrameLayout(context).apply {
        layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    val progressBar = ProgressBar(context).apply {
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER)
    }

    override val page: WebPage
        get() {
            checkThread()
            return webView.page
        }

    override val history: ArrayList<out WebPage>
        get() {
            checkThread()
            return webView.history
        }

    override var listener: IWebViewListener?
        get() {
            checkThread()
            return webView.listener
        }
        set(value) {
            checkThread()
            webView.listener = value
        }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(BaseAppCompatWebLayout::class.java)
    }

    @Suppress("unused")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(BaseAppCompatWebLayout::class.java)
    }

    open fun init(clss: Class<out IAppCompatWebView>) {
        frameLayout.addView(progressBar)
        swipeRefreshLayout.addView(frameLayout)
        addView(swipeRefreshLayout)
    }

    override fun onResume() {
        checkThread()
        webView.onResume()
    }

    override fun loadUrl(url: String?) {
        checkThread()
        webView.loadUrl(url)
    }

    override fun reload() {
        checkThread()
        webView.reload()
    }

    override fun navigateBack(): Boolean {
        checkThread()
        return webView.navigateBack()
    }

    override fun navigateForward(): Boolean {
        checkThread()
        return webView.navigateForward()
    }

    override fun clearHistory() {
        checkThread()
        webView.clearHistory()
    }

    override fun onPause() {
        checkThread()
        webView.onPause()
    }

    override fun onDestroy() {
        checkThread()
        try {
            removeAllViews()
        } catch (e: Exception) {
        }
        webView.onDestroy()
    }

    override fun hasOverlappingRendering(): Boolean {
        return false
    }
}
