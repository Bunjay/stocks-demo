package com.bunjay.stocksdemo.common.di

import com.bunjay.stocksdemo.app.data.repository.ThemeRepositoryImpl
import com.bunjay.stocksdemo.app.domain.ThemeRepository
import com.bunjay.stocksdemo.feature.stock.data.repository.StockRepositoryImpl
import com.bunjay.stocksdemo.feature.stock.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindStockRepository(impl: StockRepositoryImpl): StockRepository

    @Binds
    abstract fun bindThemeRepository(impl: ThemeRepositoryImpl): ThemeRepository
}
