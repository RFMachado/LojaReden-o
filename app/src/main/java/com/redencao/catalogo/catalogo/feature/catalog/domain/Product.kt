package com.redencao.catalogo.catalogo.feature.catalog.domain

data class Product (
    val id: Long,
    val title: String,
    val type: String,
    val size: String,
    val color: String,
    val images: List<String>
)