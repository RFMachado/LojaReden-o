package com.redencao.catalogo.catalogo.feature.catalog.repository.mapper

import android.net.Uri
import com.redencao.catalogo.catalogo.feature.catalog.domain.Product
import com.redencao.catalogo.catalogo.feature.database.model.ProductData

object CatalogMapper {
    fun map(productData: List<ProductData>) = productData.map { map(it) }

    fun map(productData: ProductData) = Product(
        id =  productData.id,
        title = productData.title,
        type = productData.type,
        size =  productData.size,
        color = productData.color,
        images = mapImages(productData)
    )

    fun mapImages(productData: ProductData): List<Uri> {
        val listImages = arrayListOf<Uri>()

        listImages.add(Uri.parse(productData.image1))
        listImages.add(Uri.parse(productData.image2))
        listImages.add(Uri.parse(productData.image3))

        return listImages
    }

}