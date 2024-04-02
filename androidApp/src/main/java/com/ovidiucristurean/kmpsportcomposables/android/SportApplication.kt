package com.ovidiucristurean.kmpsportcomposables.android

import android.app.Application
import com.ovidiucristurean.kmpsportcomposables.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class SportApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SportApplication)
            modules(
                modules = appModule
            )
        }
    }

}