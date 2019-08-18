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

    private var space = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MenuActivityUI().setContentView(this)
        space = dip(16)
        addItem("https://html5test.com")
    }

    private fun addItem(url: String, bottomSpace: Int = 0) = with(AnkoContext.createDelegate(list)) {
        cardView {
            lparams(width = matchParent, height = wrapContent) {
                setMargins(space, space, space, bottomSpace)
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
                text = url
            }.lparams(width = matchParent, height = wrapContent)
        }
    }
}
