package com.redencao.catalogo.catalogo

import android.app.Application
import android.arch.persistence.room.Room
import com.redencao.catalogo.catalogo.database.AppDatabase

class RedencaoApplication: Application() {

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, AppDatabase::class.java, "redencaoDatabase")
            .allowMainThreadQueries()
            .build()
    }

}