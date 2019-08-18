/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents

import android.os.Bundle
import android.view.ViewGroup
import androidovshchik.webcomponents.models.WebEngine
import org.xwalk.core.XWalkActivityDelegate

@Suppress("unused", "UNUSED_PARAMETER")
open class AppCompatWebActivity : BaseAppCompatWebActivity() {

    override val engine
        get() = WebEngine.CROSSWALK

    override lateinit var webLayout: BaseAppCompatWebLayout

    override var isReady
        get() = activityDelegate.isXWalkReady
        set(value) {}

    lateinit var activityDelegate: XWalkActivityDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDelegate = XWalkActivityDelegate(this, Runnable {
            onFatalError()
        }, Runnable {
            onReadyEvent()
        })
        webLayout = AppCompatWebLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        setContentView(webLayout)
    }

    override fun onResume() {
        super.onResume()
        activityDelegate.onResume()
    }
}
