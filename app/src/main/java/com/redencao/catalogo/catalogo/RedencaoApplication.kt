package com.redencao.catalogo.catalogo

import android.app.Application
import com.redencao.catalogo.catalogo.feature.di.appModule
import com.redencao.catalogo.catalogo.feature.di.dbModule
import org.koin.android.ext.android.startKoin

class RedencaoApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(dbModule, appModule))
    }

}