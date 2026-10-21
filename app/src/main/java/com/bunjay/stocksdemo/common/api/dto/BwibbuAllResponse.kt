package com.bunjay.stocksdemo.common.api.dto

import kotlinx.serialization.SerialName

data class BwibbuAllResponse(
    @SerialName("Date") val date: String? = null,
    @SerialName("Code") val code: String? = null,
    @SerialName("PEratio") val peRatio: String? = null,
    @SerialName("DividendYield") val dividendYield: String? = null,
    @SerialName("PBratio") val pbRatio: String? = null,
) {
}