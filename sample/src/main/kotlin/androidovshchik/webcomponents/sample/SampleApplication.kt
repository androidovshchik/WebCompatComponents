/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents.sample

import android.app.Application
import timber.log.Timber

@Suppress("unused")
class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
