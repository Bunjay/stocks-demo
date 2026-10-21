package com.bunjay.stocksdemo.feature.stock.domain.repository

import com.bunjay.stocksdemo.feature.stock.domain.model.Stock

interface StockRepository {
    suspend fun getStocks(): Result<List<Stock>>
}