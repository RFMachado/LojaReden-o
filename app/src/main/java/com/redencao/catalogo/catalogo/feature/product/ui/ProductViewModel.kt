package com.redencao.catalogo.catalogo.feature.product.ui

import android.arch.lifecycle.MutableLiveData
import com.redencao.catalogo.catalogo.feature.database.model.ProductData
import com.redencao.catalogo.catalogo.feature.product.domain.ProductSource
import com.redencao.catalogo.catalogo.util.rx.ReactiveViewModel

class ProductViewModel(val source: ProductSource): ReactiveViewModel() {

    val uiData = MutableLiveData<ResultUIModel>()

    fun addProduct(product: ProductData) {
        //disposables +=
    }
}