package com.redencao.catalogo.catalogo.feature.catalog.domain

data class Product (
    val id: Long,
    val description: String,
    val category: String,
    val size: String,
    val color: String,
    val value: Double,
    val images: List<String>
)