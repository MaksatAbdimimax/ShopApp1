package com.geeks.shopapp1.ui

import android.app.Application
import com.geeks.shopapp1.data.di.dataModule
import com.geeks.shopapp1.domain.di.domainModule
import com.geeks.shopapp1.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(androidContext = this@App)

            modules(
                dataModule,
                domainModule,
                uiModule
            )
        }
    }
}