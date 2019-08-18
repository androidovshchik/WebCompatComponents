/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents

import android.os.Bundle
import android.view.ViewGroup

open class WebCompatActivity : BaseWebCompatActivity() {

    override val engine
        get() = WebEngine.NATIVE

    lateinit var webLayout: BaseWebCompatLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webLayout = WebCompatLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        setContentView(webLayout)
        onReadyEvent()
    }
}
