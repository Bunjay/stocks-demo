package com.bunjay.stocksdemo.feature.stock.ui.util

import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal

class PriceTrendTest {

    private fun bd(value: String) = BigDecimal(value)

    @Test
    fun `above reference is UP`() {
        Assert.assertEquals(PriceTrend.UP, trendAgainst(bd("15.01"), bd("14.00")))
    }

    @Test
    fun `below reference is DOWN`() {
        Assert.assertEquals(PriceTrend.DOWN, trendAgainst(bd("15.01"), bd("15.89")))
    }

    @Test
    fun `equal with different scale is NEUTRAL`() {
        Assert.assertEquals(PriceTrend.NEUTRAL, trendAgainst(bd("15.0"), bd("15.00")))
    }

    @Test
    fun `null value or reference is NEUTRAL`() {
        Assert.assertEquals(PriceTrend.NEUTRAL, trendAgainst(null, bd("1")))
        Assert.assertEquals(PriceTrend.NEUTRAL, trendAgainst(bd("1"), null))
    }

    @Test
    fun `positive change is UP`() {
        Assert.assertEquals(PriceTrend.UP, trendOfChange(bd("0.0700")))
    }

    @Test
    fun `negative change is DOWN`() {
        Assert.assertEquals(PriceTrend.DOWN, trendOfChange(bd("-1.2")))
    }

    @Test
    fun `zero or null change is NEUTRAL`() {
        Assert.assertEquals(PriceTrend.NEUTRAL, trendOfChange(bd("0.0000")))
        Assert.assertEquals(PriceTrend.NEUTRAL, trendOfChange(null))
    }

    @Test
    fun `change rounding to zero is NEUTRAL`() {
        Assert.assertEquals(PriceTrend.NEUTRAL, trendOfChange(bd("0.004")))
    }
}