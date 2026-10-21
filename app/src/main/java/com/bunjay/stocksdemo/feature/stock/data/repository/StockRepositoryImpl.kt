package com.bunjay.stocksdemo.feature.stock.data.repository

import com.bunjay.stocksdemo.common.api.TwseApi
import com.bunjay.stocksdemo.feature.stock.data.mapper.toStock
import com.bunjay.stocksdemo.feature.stock.domain.model.Stock
import com.bunjay.stocksdemo.feature.stock.domain.repository.StockRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val api: TwseApi,
) : StockRepository {

    override suspend fun getStocks(): Result<List<Stock>> = try {
        coroutineScope {
            val day = async { api.getStockDayAll() }
            val avg = async { optional { api.getStockDayAvgAll() } }
            val bwibbu = async { optional { api.getBwibbuAll() } }
            val avgByCodeMap = avg.await().associateBy { it.code }
            val bwibbuByCodeMap = bwibbu.await().associateBy { it.code }
            val stocks = day.await()
                .mapNotNull { it.toStock(avgByCodeMap[it.code], bwibbuByCodeMap[it.code]) }
                .distinctBy { it.code }
            Result.success(stocks)
        }
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }

    private suspend fun <T> optional(block: suspend () -> List<T>): List<T> = try {
        block()
    } catch (e: CancellationException) {
        throw e
    } catch (_: Exception) {
        emptyList()
    }
}
