/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents

import android.os.Bundle
import android.view.ViewGroup
import androidovshchik.webcomponents.models.WebEngine

open class AppCompatWebActivity : BaseAppCompatWebActivity() {

    override val engine
        get() = WebEngine.NATIVE

    override lateinit var webLayout: BaseAppCompatWebLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webLayout = AppCompatWebLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        setContentView(webLayout)
        onReadyEvent()
    }
}
