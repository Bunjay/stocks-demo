package com.bunjay.stocksdemo.common.api

import com.bunjay.stocksdemo.common.api.dto.BwibbuAllResponse
import com.bunjay.stocksdemo.common.api.dto.StockDayAllResponse
import com.bunjay.stocksdemo.common.api.dto.StockDayAvgAllResponse
import retrofit2.http.GET

interface TwseApi {

    @GET("exchangeReport/STOCK_DAY_ALL")
    suspend fun getStockDayAll(): List<StockDayAllResponse>

    @GET("exchangeReport/STOCK_DAY_AVG_ALL")
    suspend fun getStockDayAvgAll(): List<StockDayAvgAllResponse>

    @GET("exchangeReport/BWIBBU_ALL")
    suspend fun getBwibbuAll(): List<BwibbuAllResponse>
}