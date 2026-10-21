package com.bunjay.stocksdemo.feature.stock.data.mapper

import com.bunjay.stocksdemo.common.api.dto.BwibbuAllResponse
import com.bunjay.stocksdemo.common.api.dto.StockDayAllResponse
import com.bunjay.stocksdemo.common.api.dto.StockDayAvgAllResponse
import com.bunjay.stocksdemo.feature.stock.domain.model.Stock

fun StockDayAllResponse.toStock(avg: StockDayAvgAllResponse?, bwibbu: BwibbuAllResponse?): Stock? {
    val code = code?.trim()
    if (code.isNullOrEmpty()) return null
    return Stock(
        code = code,
        name = name.orEmpty(),
        openingPrice = openingPrice?.toBigDecimalOrNull(),
        closingPrice = closingPrice?.toBigDecimalOrNull(),
        highestPrice = highestPrice?.toBigDecimalOrNull(),
        lowestPrice = lowestPrice?.toBigDecimalOrNull(),
        change = change?.toBigDecimalOrNull(),
        monthlyAveragePrice = avg?.monthlyAveragePrice?.toBigDecimalOrNull(),
        transaction = transaction?.toBigDecimalOrNull(),
        tradeVolume = tradeVolume?.toBigDecimalOrNull(),
        tradeValue = tradeValue?.toBigDecimalOrNull(),
        peRatio = bwibbu?.peRatio?.toBigDecimalOrNull(),
        dividendYield = bwibbu?.dividendYield?.toBigDecimalOrNull(),
        pbRatio = bwibbu?.pbRatio?.toBigDecimalOrNull(),
    )
}