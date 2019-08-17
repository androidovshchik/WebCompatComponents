/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents

import android.content.Context
import android.view.ViewGroup
import androidovshchik.webcomponents.crosswalk.BuildConfig
import androidovshchik.webcomponents.exceptions.InvalidThreadException
import androidovshchik.webcomponents.extensions.checkThread
import androidovshchik.webcomponents.models.WebEngine
import androidovshchik.webcomponents.models.WebPage
import org.xwalk.core.XWalkNavigationHistory
import org.xwalk.core.XWalkView

@Suppress("LeakingThis", "unused")
open class AppCompatWebView(context: Context) : XWalkView(context), IAppCompatWebView {

    override val engine: WebEngine
        get() {
            checkThread()
            return WebEngine.CROSSWALK
        }

    override val page: WebPage
        get() {
            checkThread()
            return navigationHistory.currentItem?.let {
                WebPage(it.url, null, it.title)
            } ?: WebPage()
        }

    override val history: ArrayList<out WebPage>
        @Throws(InvalidThreadException::class)
        get() {
            checkThread()
            val size = navigationHistory.size()
            val pages = arrayListOf<WebPage>()
            for (i in 0 until size) {
                navigationHistory.getItemAt(i)?.let {
                    pages.add(WebPage(it.url, null, it.title))
                }
            }
            return pages
        }

    override var listener: IWebViewListener? = null

    init {
        init(AppCompatWebView::class.java)
    }

    open fun init(clss: Class<out IAppCompatWebView>) {
        if (isInEditMode) {
            return
        }
        setUIClient(CrosswalkUIClient(this))
        setResourceClient(CrosswalkResourceClient(this))
    }

    @Throws(InvalidThreadException::class)
    override fun onResume() {
        checkThread()
        if (!BuildConfig.LITE) {
            // unsupported in lite version
            resumeTimers()
        }
    }

    @Suppress("DEPRECATION")
    @Throws(InvalidThreadException::class)
    override fun loadUrl(url: String?) {
        checkThread()
        load(url.toString(), null)
    }

    @Throws(InvalidThreadException::class)
    override fun reload() {
        checkThread()
        reload(RELOAD_NORMAL)
    }

    @Throws(InvalidThreadException::class)
    override fun navigateBack(): Boolean {
        checkThread()
        return if (navigationHistory.canGoBack()) {
            navigationHistory.navigate(XWalkNavigationHistory.Direction.BACKWARD, 1)
            true
        } else {
            false
        }
    }

    @Throws(InvalidThreadException::class)
    override fun navigateForward(): Boolean {
        checkThread()
        return if (navigationHistory.canGoForward()) {
            navigationHistory.navigate(XWalkNavigationHistory.Direction.FORWARD, 1)
            true
        } else {
            false
        }
    }

    @Throws(InvalidThreadException::class)
    override fun clearHistory() {
        checkThread()
        navigationHistory.clear()
    }

    @Throws(InvalidThreadException::class)
    override fun onPause() {
        checkThread()
        pauseTimers()
    }

    @Throws(InvalidThreadException::class)
    override fun onDestroy() {
        checkThread()
        try {
            (parent as ViewGroup?)?.removeView(this)
        } catch (e: Exception) {
        }
        try {
            removeAllViews()
        } catch (e: Exception) {
        }
    }

    override fun hasOverlappingRendering(): Boolean {
        return false
    }
}
