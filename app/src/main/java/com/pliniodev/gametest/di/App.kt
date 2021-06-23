package com.pliniodev.gametest.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initiateKoin()
    }

    private fun initiateKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)

            modules(
                presentationModules,
                networkModules,
                dataModules,
                domainModules,
                databaseModule,
                sharedPrefModule
            )
        }
    }
}