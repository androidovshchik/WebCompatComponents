/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("DEPRECATION")

package com.example.app

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import org.mozilla.geckoview.*

/**
 * https://searchfox.org/mozilla-central/source/mobile/android/geckoview_example/src/main/java/org/mozilla/geckoview_example
 */
class WebActivity : AppCompatActivity() {

    private lateinit var webView: GeckoView

    private lateinit var runtime: GeckoRuntime

    private var canBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runtime = GeckoRuntime.create(applicationContext)
        val session = GeckoSession().apply {
            progressDelegate = ProgressDelegate()
            navigationDelegate = NavigationDelegate()
            open(runtime)
        }
        webView = GeckoView(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            setSession(session)
        }
        setContentView(FrameLayout(applicationContext).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            addView(webView)
            if (intent.getBooleanExtra("progress", false)) {
                addView(ProgressBar(applicationContext).apply {
                    layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        Gravity.CENTER
                    )
                    isIndeterminate = true
                    indeterminateDrawable.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.colorAccent),
                        PorterDuff.Mode.SRC_IN
                    )
                })
            }
        })
        session.loadUri(intent.getStringExtra("url") ?: return)
    }

    override fun onBackPressed() {
        webView.session?.apply {
            if (canBack) {
                goBack()
                return
            }
        }
        super.onBackPressed()
    }

    private inner class NavigationDelegate : GeckoSession.NavigationDelegate {

        override fun onLoadRequest(
            session: GeckoSession,
            request: GeckoSession.NavigationDelegate.LoadRequest
        ): GeckoResult<AllowOrDeny>? {
            return GeckoResult.fromValue(AllowOrDeny.ALLOW)
        }

        override fun onNewSession(session: GeckoSession, uri: String): GeckoResult<GeckoSession>? {
            return GeckoResult.fromValue(GeckoSession().apply {
                progressDelegate = ProgressDelegate()
                navigationDelegate = NavigationDelegate()
                open(runtime)
            })
        }

        override fun onCanGoBack(session: GeckoSession, canGoBack: Boolean) {
            canBack = canGoBack
        }
    }

    private inner class ProgressDelegate : GeckoSession.ProgressDelegate {

        override fun onPageStop(session: GeckoSession, success: Boolean) {
            hideProgress()
        }

        override fun onProgressChange(session: GeckoSession, progress: Int) {
            if (progress > 40) {
                hideProgress()
            }
        }

        private fun hideProgress() {
            (webView.parent as FrameLayout).apply {
                for (i in 0 until childCount) {
                    getChildAt(i).apply {
                        if (this is ProgressBar) {
                            if (visibility != View.GONE) {
                                visibility = View.GONE
                            }
                        }
                    }
                }
            }
        }
    }
}