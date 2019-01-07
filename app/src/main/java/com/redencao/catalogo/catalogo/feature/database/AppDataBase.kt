package com.redencao.catalogo.catalogo.feature.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.redencao.catalogo.catalogo.feature.database.model.ProductData
import com.redencao.catalogo.catalogo.util.Converters

@Database(entities = [ProductData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDataDao
}