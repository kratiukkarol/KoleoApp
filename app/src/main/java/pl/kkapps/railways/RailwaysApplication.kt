package pl.kkapps.railways

import android.app.Application
import com.airbnb.mvrx.Mavericks
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RailwaysApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}