package com.bunjay.stocksdemo.feature.stock.ui.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

fun formatPrice(value: BigDecimal?): String =
    value?.setScale(2, RoundingMode.HALF_UP)?.toPlainString() ?: "-"

fun formatChange(value: BigDecimal?): String {
    val scaled = value?.setScale(2, RoundingMode.HALF_UP) ?: return "-"
    val plain = scaled.abs().toPlainString()
    return when (scaled.signum()) {
        1 -> "▲$plain"
        -1 -> "▼$plain"
        else -> plain
    }
}

fun formatCount(value: BigDecimal?): String =
    value?.setScale(0, RoundingMode.HALF_UP)?.let {
        DecimalFormat("#,##0").format(it)
    } ?: "-"
