package com.bunjay.stocksdemo.feature.stock.domain.usecase
import com.bunjay.stocksdemo.feature.stock.domain.model.Stock
import com.bunjay.stocksdemo.feature.stock.domain.repository.StockRepository
import javax.inject.Inject

class GetStocksUseCase @Inject constructor(
    private val repository: StockRepository,
) {
    suspend operator fun invoke(): Result<List<Stock>> = repository.getStocks()
}
