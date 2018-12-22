package com.redencao.catalogo.catalogo.feature.catalog.repository

import com.redencao.catalogo.catalogo.feature.catalog.domain.CatalogSource
import com.redencao.catalogo.catalogo.feature.catalog.domain.Product
import com.redencao.catalogo.catalogo.feature.catalog.repository.mapper.CatalogMapper
import com.redencao.catalogo.catalogo.feature.database.ProductDataDao
import io.reactivex.Single

class CatalogRepository(private val productDataDao: ProductDataDao): CatalogSource {

    override fun fetchAll(): Single<List<Product>> {
        return productDataDao.fetchAll()
            .map { CatalogMapper.map(it) }
    }

}