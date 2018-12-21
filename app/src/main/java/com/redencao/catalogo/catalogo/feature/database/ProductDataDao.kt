package com.redencao.catalogo.catalogo.feature.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.redencao.catalogo.catalogo.feature.database.model.ProductData

@Dao
interface ProductDataDao {

    @Query("SELECT * from product")
    fun getAll(): List<ProductData>

    @Insert(onConflict = REPLACE)
    fun insert(product: ProductData)

    @Delete
    fun deleteProduct(product: ProductData)

}