package com.bunjay.stocksdemo.common.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StockDayAllResponse(
    @SerialName("Date") val date: String? = null,
    @SerialName("Code") val code: String? = null,
    @SerialName("Name") val name: String? = null,
    @SerialName("TradeVolume") val tradeVolume: String? = null,
    @SerialName("TradeValue") val tradeValue: String? = null,
    @SerialName("OpeningPrice") val openingPrice: String? = null,
    @SerialName("HighestPrice") val highestPrice: String? = null,
    @SerialName("LowestPrice") val lowestPrice: String? = null,
    @SerialName("ClosingPrice") val closingPrice: String? = null,
    @SerialName("Change") val change: String? = null,
    @SerialName("Transaction") val transaction: String? = null,
)
