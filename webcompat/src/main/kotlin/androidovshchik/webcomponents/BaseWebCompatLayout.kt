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
import androidovshchik.webcomponents.extensions.isUIThread
import androidovshchik.webcomponents.models.WebPage
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@Suppress("LeakingThis", "MemberVisibilityCanBePrivate")
abstract class BaseWebCompatLayout : FrameLayout, IWebCompatLayout {

    abstract var webCompatView: IWebCompatView

    val swipeLayout = SwipeRefreshLayout(context).apply {
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
            return webCompatView.page
        }

    override val history: ArrayList<out WebPage>
        get() {
            return webCompatView.history
        }

    override val listeners: HashSet<IWebViewListener>
        get() {
            return webCompatView.listeners
        }

    override var enableSwipeLayout = true
        set(value) {
            field = value

        }

    override var enableProgressBar = true
        set(value) {
            field = value

        }

    override var accentColor: Int
        set(value) {
            field = value

        }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(BaseWebCompatLayout::class.java)
    }

    @Suppress("unused")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(BaseWebCompatLayout::class.java)
    }

    open fun init(clss: Class<out IWebCompatView>) {
        frameLayout.addView(progressBar)
        swipeLayout.addView(frameLayout)
        addView(swipeLayout)
    }

    override fun onResume() {
        webCompatView.onResume()
    }

    override fun load(data: CharSequence?) {
        webCompatView.load(data)
    }

    override fun reload() {
        webCompatView.reload()
    }

    override fun navigate(steps: Int): Boolean {
        return webCompatView.navigate(steps)
    }

    override fun onPause() {
        webCompatView.onPause()
    }

    override fun onDestroy() {
        if (isUIThread) {
            try {
                removeAllViews()
            } catch (e: Exception) {
            }
        }
        synchronized(this) {
            listeners.clear()
        }
        webCompatView.onDestroy()
    }

    override fun hasOverlappingRendering(): Boolean {
        return false
    }
}
