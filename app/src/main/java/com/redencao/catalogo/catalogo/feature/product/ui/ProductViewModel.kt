package com.redencao.catalogo.catalogo.feature.product.ui

import android.arch.lifecycle.MutableLiveData
import com.redencao.catalogo.catalogo.feature.database.model.ProductData
import com.redencao.catalogo.catalogo.feature.product.domain.ProductSource
import com.redencao.catalogo.catalogo.util.rx.ReactiveViewModel
import com.redencao.catalogo.catalogo.util.rx.RxUtils
import io.reactivex.rxkotlin.plusAssign

class ProductViewModel(val source: ProductSource): ReactiveViewModel() {

    val uiData = MutableLiveData<ResultUIModel>()

    fun addProduct(product: ProductData) {
        disposables += source.insert(product)
            .compose(RxUtils.applyCompletableSchedulers())
            .subscribe(
                { },
                { uiData.value = ResultUIModel(error = it) }
            )
    }

    data class ResultUIModel(val data: Boolean = false, val error: Throwable? = null)
}