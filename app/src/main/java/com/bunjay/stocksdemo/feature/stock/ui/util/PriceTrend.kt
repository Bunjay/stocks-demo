package com.bunjay.stocksdemo.feature.stock.ui.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.bunjay.stocksdemo.app.ui.theme.LocalAppColors
import java.math.BigDecimal
import java.math.RoundingMode

enum class PriceTrend { UP, DOWN, NEUTRAL }

fun trendAgainst(value: BigDecimal?, reference: BigDecimal?): PriceTrend {
    if (value == null || reference == null) return PriceTrend.NEUTRAL
    return value.compareTo(reference).toTrend()
}

fun trendOfChange(change: BigDecimal?): PriceTrend =
    change?.setScale(2, RoundingMode.HALF_UP)?.signum()?.toTrend() ?: PriceTrend.NEUTRAL

private fun Int.toTrend() = when {
    this > 0 -> PriceTrend.UP
    this < 0 -> PriceTrend.DOWN
    else -> PriceTrend.NEUTRAL
}

@Composable
fun PriceTrend.color(): Color = when (this) {
    PriceTrend.UP -> LocalAppColors.current.stockUp
    PriceTrend.DOWN -> LocalAppColors.current.stockDown
    PriceTrend.NEUTRAL -> MaterialTheme.colorScheme.onSurface
}
