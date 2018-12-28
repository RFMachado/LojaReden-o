package com.redencao.catalogo.catalogo.feature.product.create.repository

import com.redencao.catalogo.catalogo.feature.database.ProductDataDao
import com.redencao.catalogo.catalogo.feature.database.model.ProductData
import com.redencao.catalogo.catalogo.feature.product.create.domain.ProductSource
import io.reactivex.Completable
import io.reactivex.Single

class ProductRepository(private val productDataDao: ProductDataDao): ProductSource {

    override fun fetchAll(): Single<List<ProductData>> {
        return productDataDao.fetchAll()
    }

    override fun insert(product: ProductData): Completable {
        return Completable.fromCallable {
            productDataDao.insert(product)
        }
    }

    override fun delete(product: ProductData): Completable {
        return Completable.fromCallable {
            productDataDao.delete(product)
        }
    }

}