package com.redencao.catalogo.catalogo.feature.di

import android.arch.persistence.room.Room
import com.redencao.catalogo.catalogo.feature.database.AppDatabase
import com.redencao.catalogo.catalogo.feature.product.domain.ProductSource
import com.redencao.catalogo.catalogo.feature.product.repository.ProductRepository
import com.redencao.catalogo.catalogo.feature.product.ui.ProductViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val dbModule = module {
    single { Room.databaseBuilder(
        get(),
        AppDatabase::class.java,
        "redencao-database")
        .build()
    }

    single { get<AppDatabase>().productDao() }
}

val appModule = module {
    single<ProductSource> { ProductRepository(get()) }

    viewModel { ProductViewModel(get()) }
}