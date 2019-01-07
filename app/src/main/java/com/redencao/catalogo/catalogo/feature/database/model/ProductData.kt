package com.redencao.catalogo.catalogo.feature.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var description: String,
    var category: String,
    var size: String,
    var color: String,
    val value: Double,
    val images: List<String>
)