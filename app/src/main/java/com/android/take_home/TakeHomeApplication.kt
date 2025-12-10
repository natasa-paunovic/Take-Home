package com.android.take_home

import android.app.Application
import com.android.take_home.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class TakeHomeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TakeHomeApplication)
            modules(appModules)
        }
    }

}