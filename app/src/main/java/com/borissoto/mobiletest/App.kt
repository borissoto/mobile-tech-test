package com.borissoto.mobiletest

import android.app.Application
import com.borissoto.mobiletest.di.AppComponent
import com.borissoto.mobiletest.di.DaggerAppComponent


class App : Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
            .factory()
            .create(this)
    }
}