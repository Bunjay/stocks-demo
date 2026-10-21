package com.bunjay.stocksdemo.feature.stock.ui.component
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bunjay.stocksdemo.R
import com.bunjay.stocksdemo.feature.stock.domain.model.Stock
import com.bunjay.stocksdemo.feature.stock.ui.util.formatPrice

@Composable
fun StockInfoDialog(stock: Stock, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("${stock.code} ${stock.name}") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                InfoRow(stringResource(R.string.label_pe_ratio), formatPrice(stock.peRatio))
                InfoRow(stringResource(R.string.label_dividend_yield), formatPrice(stock.dividendYield))
                InfoRow(stringResource(R.string.label_pb_ratio), formatPrice(stock.pbRatio))
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.action_ok)) }
        },
    )
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text("$label：", style = MaterialTheme.typography.bodyLarge)
        Text(value, style = MaterialTheme.typography.bodyLarge)
    }
}
