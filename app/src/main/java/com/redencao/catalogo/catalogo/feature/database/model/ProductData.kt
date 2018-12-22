package com.redencao.catalogo.catalogo.feature.database.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "product")
data class ProductData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var title: String,
    var type: String,
    var size: String,
    var cor: String
)