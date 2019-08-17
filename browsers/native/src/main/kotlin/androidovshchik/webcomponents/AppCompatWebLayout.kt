/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidovshchik.webcomponents.models.WebEngine

@Suppress("LeakingThis")
open class AppCompatWebLayout : BaseAppCompatWebLayout {

    override val engine: WebEngine
        get() {
            return WebEngine.NATIVE
        }

    override var webView: IAppCompatWebView = AppCompatWebView(context).apply {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(AppCompatWebLayout::class.java)
    }

    @Suppress("unused")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(AppCompatWebLayout::class.java)
    }

    override fun init(clss: Class<out IAppCompatWebView>) {
        if (clss == AppCompatWebLayout::class.java) {
            frameLayout.addView(webView as View, 0)
        } else {
            super.init(clss)
        }
    }
}