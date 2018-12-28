package com.redencao.catalogo.catalogo.feature.di

import android.arch.persistence.room.Room
import com.redencao.catalogo.catalogo.feature.catalog.domain.CatalogSource
import com.redencao.catalogo.catalogo.feature.catalog.repository.CatalogRepository
import com.redencao.catalogo.catalogo.feature.catalog.ui.CatalogViewModel
import com.redencao.catalogo.catalogo.feature.database.AppDatabase
import com.redencao.catalogo.catalogo.feature.product.create.domain.ProductSource
import com.redencao.catalogo.catalogo.feature.product.create.repository.ProductRepository
import com.redencao.catalogo.catalogo.feature.product.create.ui.ProductViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val dbModule = module {
    single { Room.databaseBuilder(
        get(),
        AppDatabase::class.java,
        "redencao-database")
        .fallbackToDestructiveMigration()
        .build()
    }

    single { get<AppDatabase>().productDao() }
}

val appModule = module {
    single<ProductSource> { ProductRepository(get()) }
    single<CatalogSource> { CatalogRepository(get()) }

    viewModel { ProductViewModel(get()) }
    viewModel { CatalogViewModel(get()) }
}