package com.redencao.catalogo.catalogo.feature.database.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.net.Uri

@Entity(tableName = "product")
data class ProductData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var title: String,
    var type: String,
    var size: String,
    var color: String,
    val image1: String,
    val image2: String,
    val image3: String
)