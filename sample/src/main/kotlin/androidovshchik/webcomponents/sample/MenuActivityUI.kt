/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents.sample

import android.view.View
import org.jetbrains.anko.*

class MenuActivityUI : AnkoComponent<MenuActivity> {

    override fun createView(ui: AnkoContext<MenuActivity>): View = with(ui) {
        scrollView {
            lparams(width = matchParent, height = matchParent)
            owner.list = verticalLayout().lparams(width = matchParent, height = wrapContent)
        }
    }
}