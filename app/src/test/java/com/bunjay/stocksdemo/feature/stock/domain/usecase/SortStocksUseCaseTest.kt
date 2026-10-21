package com.bunjay.stocksdemo.feature.stock.domain.usecase

import com.bunjay.stocksdemo.feature.stock.domain.model.Stock
import com.bunjay.stocksdemo.feature.stock.domain.model.StockSort
import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal

class SortStocksUseCaseTest {
    private val stocks = listOf(testStock("1101"), testStock("00400A"), testStock("2330"))

    @Test
    fun `DESC sorts by code`() {
        Assert.assertEquals(
            listOf("2330", "1101", "00400A"),
            SortStocksUseCase()(stocks, StockSort.BY_CODE_DESC).map { it.code })
    }

    @Test
    fun `ASC sorts by code`() {
        Assert.assertEquals(
            listOf("00400A", "1101", "2330"),
            SortStocksUseCase()(stocks, StockSort.BY_CODE_ASC).map { it.code })
    }
}

fun testStock(
    code: String,
    name: String = "N$code",
    openingPrice: BigDecimal? = null,
    closingPrice: BigDecimal? = null,
    highestPrice: BigDecimal? = null,
    lowestPrice: BigDecimal? = null,
    change: BigDecimal? = null,
    monthlyAveragePrice: BigDecimal? = null,
    transaction: BigDecimal? = null,
    tradeVolume: BigDecimal? = null,
    tradeValue: BigDecimal? = null,
    peRatio: BigDecimal? = null,
    dividendYield: BigDecimal? = null,
    pbRatio: BigDecimal? = null,
) = Stock(
    code = code,
    name = name,
    openingPrice = openingPrice,
    closingPrice = closingPrice,
    highestPrice = highestPrice,
    lowestPrice = lowestPrice,
    change = change,
    monthlyAveragePrice = monthlyAveragePrice,
    transaction = transaction,
    tradeVolume = tradeVolume,
    tradeValue = tradeValue,
    peRatio = peRatio,
    dividendYield = dividendYield,
    pbRatio = pbRatio,
)


