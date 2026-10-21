package com.bunjay.stocksdemo.feature.stock.domain.usecase
import com.bunjay.stocksdemo.feature.stock.domain.model.Stock
import com.bunjay.stocksdemo.feature.stock.domain.model.StockSort
import javax.inject.Inject

class SortStocksUseCase @Inject constructor() {
    operator fun invoke(stocks: List<Stock>, sort: StockSort): List<Stock> = when (sort) {
        StockSort.BY_CODE_DESC -> stocks.sortedByDescending { it.code }
        StockSort.BY_CODE_ASC -> stocks.sortedBy { it.code }
    }
}
