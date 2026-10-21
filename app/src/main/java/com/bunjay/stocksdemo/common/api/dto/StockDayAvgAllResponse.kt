package com.bunjay.stocksdemo.common.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StockDayAvgAllResponse(
    @SerialName("Code") val code: String? = null,
    @SerialName("ClosingPrice") val closingPrice: String? = null,
    @SerialName("MonthlyAveragePrice") val monthlyAveragePrice: String? = null,
)
