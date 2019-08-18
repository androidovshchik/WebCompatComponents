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

@Suppress("LeakingThis")
open class WebCompatLayout : BaseWebCompatLayout {

    override val browser: WebEngine
        get() {
            return WebEngine.CROSSWALK
        }

    var webCompatView: IWebCompatView = WebCompatView(context).apply {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(WebCompatLayout::class.java)
    }

    @Suppress("unused")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(WebCompatLayout::class.java)
    }

    override fun init(clss: Class<out IWebCompatView>) {
        if (clss == WebCompatLayout::class.java) {
            frameLayout.addView(webCompatView as View, 0)
        } else {
            super.init(clss)
        }
    }
}