package com.bunjay.stocksdemo.feature.stock.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.bunjay.stocksdemo.R
import com.bunjay.stocksdemo.feature.stock.domain.model.StockSort

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortBottomSheet(
    selected: StockSort,
    onSelect: (StockSort) -> Unit,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(Modifier.padding(bottom = 24.dp)) {
            Text(
                text = stringResource(R.string.sort_title),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
            )
            Column(Modifier.selectableGroup()) {
                SortOption(
                    stringResource(R.string.sort_code_desc), selected == StockSort.BY_CODE_DESC
                ) { onSelect(StockSort.BY_CODE_DESC) }
                SortOption(
                    stringResource(R.string.sort_code_asc), selected == StockSort.BY_CODE_ASC
                ) { onSelect(StockSort.BY_CODE_ASC) }
            }
        }
    }
}

@Composable
private fun SortOption(label: String, selected: Boolean, onClick: () -> Unit) {
    ListItem(
        headlineContent = { Text(label) },
        leadingContent = { RadioButton(selected = selected, onClick = null) },
        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        modifier = Modifier
            .selectable(
                selected = selected, role = Role.RadioButton, onClick = onClick
            )
            .padding(horizontal = 8.dp),
    )
}
