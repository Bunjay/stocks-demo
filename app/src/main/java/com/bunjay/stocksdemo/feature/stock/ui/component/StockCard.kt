package com.bunjay.stocksdemo.feature.stock.ui.component

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bunjay.stocksdemo.R
import com.bunjay.stocksdemo.app.ui.theme.StocksDemoTheme
import com.bunjay.stocksdemo.feature.stock.domain.model.Stock
import com.bunjay.stocksdemo.feature.stock.ui.util.PriceTrend
import com.bunjay.stocksdemo.feature.stock.ui.util.color
import com.bunjay.stocksdemo.feature.stock.ui.util.formatChange
import com.bunjay.stocksdemo.feature.stock.ui.util.formatCount
import com.bunjay.stocksdemo.feature.stock.ui.util.formatPrice
import com.bunjay.stocksdemo.feature.stock.ui.util.trendAgainst
import com.bunjay.stocksdemo.feature.stock.ui.util.trendOfChange
import java.math.BigDecimal


@Composable
fun StockCard(stock: Stock, onClick: () -> Unit, modifier: Modifier = Modifier) {
    ElevatedCard(onClick = onClick, modifier = modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = stock.code,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = stock.name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(12.dp))
            val leftLabelWidth = rememberLabelWidth(
                R.string.label_opening_price, R.string.label_highest_price, R.string.label_change,
            )
            val rightLabelWidth = rememberLabelWidth(
                R.string.label_closing_price,
                R.string.label_lowest_price,
                R.string.label_monthly_average_price,
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row {
                    PriceCell(
                        labelWidth = leftLabelWidth,
                        label = stringResource(R.string.label_opening_price),
                        value = formatPrice(stock.openingPrice),
                        color = PriceTrend.NEUTRAL.color(),
                    )
                    PriceCell(
                        labelWidth = rightLabelWidth,
                        label = stringResource(R.string.label_closing_price),
                        value = formatPrice(stock.closingPrice),
                        color = trendAgainst(stock.closingPrice, stock.monthlyAveragePrice).color(),
                    )
                }
                Row {
                    PriceCell(
                        labelWidth = leftLabelWidth,
                        label = stringResource(R.string.label_highest_price),
                        value = formatPrice(stock.highestPrice),
                        color = PriceTrend.NEUTRAL.color(),
                    )
                    PriceCell(
                        labelWidth = rightLabelWidth,
                        label = stringResource(R.string.label_lowest_price),
                        value = formatPrice(stock.lowestPrice),
                        color = PriceTrend.NEUTRAL.color(),
                    )
                }
                Row {
                    PriceCell(
                        labelWidth = leftLabelWidth,
                        label = stringResource(R.string.label_change),
                        value = formatChange(stock.change),
                        color = trendOfChange(stock.change).color()
                    )
                    PriceCell(
                        labelWidth = rightLabelWidth,
                        label = stringResource(R.string.label_monthly_average_price),
                        value = formatPrice(stock.monthlyAveragePrice),
                        color = PriceTrend.NEUTRAL.color(),
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TradeCell(
                    stringResource(R.string.label_transaction), formatCount(stock.transaction)
                )
                TradeCell(
                    stringResource(R.string.label_trade_volume), formatCount(stock.tradeVolume)
                )
                TradeCell(stringResource(R.string.label_trade_value), formatCount(stock.tradeValue))
            }
        }
    }
}

@Composable
private fun rememberLabelWidth(@StringRes vararg labels: Int): Dp {
    val texts = labels.map { stringResource(it) }
    val style = MaterialTheme.typography.bodyMedium
    val measurer = rememberTextMeasurer()
    val density = LocalDensity.current
    return remember(texts, style, density) {
        with(density) {
            texts.maxOf { measurer.measure(it, style, maxLines = 1).size.width }.toDp()
        }
    }
}

@Composable
private fun RowScope.PriceCell(label: String, labelWidth: Dp, value: String, color: Color) {
    FlowRow(
        modifier = Modifier.weight(1f),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        itemVerticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.End,
            modifier = Modifier.width(labelWidth),
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = color,
            softWrap = false,
        )
    }
}

@Composable
private fun RowScope.TradeCell(label: String, value: String) {
    Column(Modifier.weight(1f)) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

private val previewStock = Stock(
    code = "1102",
    name = "亞泥",
    openingPrice = BigDecimal("40.50"),
    closingPrice = BigDecimal("40.10"),
    highestPrice = BigDecimal("40.60"),
    lowestPrice = BigDecimal("40.00"),
    change = BigDecimal("-0.5000"),
    monthlyAveragePrice = BigDecimal("41.00"),
    transaction = BigDecimal("7336"),
    tradeVolume = BigDecimal("27595457"),
    tradeValue = BigDecimal("415025661"),
    peRatio = BigDecimal("10.55"),
    dividendYield = BigDecimal("6.02"),
    pbRatio = BigDecimal("0.73"),
)

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun StockCardPreview() {
    StocksDemoTheme {
        StockCard(stock = previewStock, onClick = {}, modifier = Modifier.padding(16.dp))
    }
}