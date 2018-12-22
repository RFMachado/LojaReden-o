package com.redencao.catalogo.catalogo.feature.catalog.ui

import android.arch.lifecycle.MutableLiveData
import com.redencao.catalogo.catalogo.feature.catalog.domain.CatalogSource
import com.redencao.catalogo.catalogo.feature.catalog.domain.Product
import com.redencao.catalogo.catalogo.util.rx.ReactiveViewModel
import com.redencao.catalogo.catalogo.util.rx.RxUtils
import io.reactivex.rxkotlin.plusAssign

class CatalogViewModel (val source: CatalogSource): ReactiveViewModel() {

    val uiData = MutableLiveData<ResultUIModel>()

    fun fetchAllProducts() {
        disposables += source.fetchAll()
            .compose(RxUtils.applySingleSchedulers())
            .subscribe(
                {
                    uiData.value = ResultUIModel(data = it)
                },
                {
                    uiData.value = ResultUIModel(error = it)
                }
            )
    }

    data class ResultUIModel(val data: List<Product>? = null, val error: Throwable? = null)
}