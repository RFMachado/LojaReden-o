package com.redencao.catalogo.catalogo.util.rx

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class ReactiveViewModel: ViewModel() {

    val loadData = MutableLiveData<Boolean>()

    val disposables = CompositeDisposable()

    fun disposables() = disposables

    fun unbind() {
        this.disposables.clear()
    }
}