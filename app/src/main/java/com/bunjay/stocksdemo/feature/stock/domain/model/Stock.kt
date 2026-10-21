package com.bunjay.stocksdemo.feature.stock.domain.model

import java.math.BigDecimal

data class Stock(
    val code: String,
    val name: String,
    val openingPrice: BigDecimal?,
    val closingPrice: BigDecimal?,
    val highestPrice: BigDecimal?,
    val lowestPrice: BigDecimal?,
    val change: BigDecimal?,
    val monthlyAveragePrice: BigDecimal?,
    val transaction: BigDecimal?,         // 成交筆數
    val tradeVolume: BigDecimal?,         // 成交股數
    val tradeValue: BigDecimal?,          // 成交金額
    val peRatio: BigDecimal?,       // 本益比
    val dividendYield: BigDecimal?, // 殖利率(%)
    val pbRatio: BigDecimal?,       // 股價淨值比
)
