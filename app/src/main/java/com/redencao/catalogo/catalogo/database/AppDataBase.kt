package com.redencao.catalogo.catalogo.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.redencao.catalogo.catalogo.database.model.ProductData

@Database(entities = [ProductData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDataDao
}