package com.bunjay.stocksdemo.feature.stock.ui.util

import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal

class FormatPriceTest {
    @Test
    fun `formatPrice uses two decimals`() {
        Assert.assertEquals("14.90", formatPrice(BigDecimal("14.9")))
    }

    @Test
    fun `formatPrice null is dash`() {
        Assert.assertEquals("-", formatPrice(null))
    }

    @Test
    fun `formatChange adds plus sign`() {
        Assert.assertEquals("▲0.07", formatChange(BigDecimal("0.0700")))
    }

    @Test
    fun `formatChange keeps minus sign`() {
        Assert.assertEquals("▼1.20", formatChange(BigDecimal("-1.2")))
    }

    @Test
    fun `formatChange zero has no sign`() {
        Assert.assertEquals("0.00", formatChange(BigDecimal("0.0000")))
    }

    @Test
    fun `formatChange rounds to zero without sign`() {
        Assert.assertEquals("0.00", formatChange(BigDecimal("0.004")))
        Assert.assertEquals("0.00", formatChange(BigDecimal("-0.004")))
    }

    @Test
    fun `formatCount uses thousands separator`() {
        Assert.assertEquals("27,595,457", formatCount(BigDecimal(27595457L)))
    }

    @Test
    fun `formatCount null is dash`() {
        Assert.assertEquals("-", formatCount(null))
    }
}