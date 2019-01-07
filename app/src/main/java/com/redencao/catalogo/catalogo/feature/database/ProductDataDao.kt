package com.redencao.catalogo.catalogo.feature.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.redencao.catalogo.catalogo.feature.database.model.ProductData
import io.reactivex.Single

@Dao
interface ProductDataDao {

    @Query("SELECT * from product")
    fun fetchAll(): Single<List<ProductData>>

    @Insert(onConflict = REPLACE)
    fun insert(product: ProductData): Long

    @Delete
    fun delete(product: ProductData): Int

}