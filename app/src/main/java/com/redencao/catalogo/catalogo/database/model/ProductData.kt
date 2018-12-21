package com.redencao.catalogo.catalogo.database.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "product")
data class ProductData(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    var title: String,
    var type: String,
    var size: String,
    var cor: String
)