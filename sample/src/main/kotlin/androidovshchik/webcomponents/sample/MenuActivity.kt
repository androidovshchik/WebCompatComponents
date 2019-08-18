/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents.sample

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import androidovshchik.webcomponents.WebCompat
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class MenuActivity : AppCompatActivity() {

    lateinit var list: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MenuActivityUI().setContentView(this)
        addItem("https://html5test.com", "HTML5test - How well does your browser support HTML5?")
    }

    private fun addItem(url: String, title: String, bottomMargin: Int = 0) {
        val space = dip(16)
        with(AnkoContext.createDelegate(list)) {
            cardView {
                lparams(width = matchParent, height = wrapContent) {
                    setMargins(space, space, space, bottomMargin)
                }
                setOnClickListener {
                    WebCompat.IntentBuilder(applicationContext)
                        .inputData(url)
                        .startActivity(applicationContext)
                }
                textView {
                    padding = space
                    textColor = Color.WHITE
                    textSize = 16f
                    text = title
                }.lparams(width = matchParent, height = wrapContent)
            }
        }
    }
}
