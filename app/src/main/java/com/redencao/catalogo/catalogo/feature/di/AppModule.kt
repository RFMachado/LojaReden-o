package com.redencao.catalogo.catalogo.feature.di

import android.arch.persistence.room.Room
import com.redencao.catalogo.catalogo.feature.database.AppDatabase
import org.koin.dsl.module.module

val dbModule = module {
    single { Room.databaseBuilder(
        get(),
        AppDatabase::class.java,
        "redencao-database")
        .build()
    }

    single { get<AppDatabase>().productDao() }
}