package com.bunjay.stocksdemo.feature.stock.ui.state

import androidx.annotation.StringRes
import com.bunjay.stocksdemo.feature.stock.domain.model.Stock
import com.bunjay.stocksdemo.feature.stock.domain.model.StockSort


data class StockListUiState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val stocks: List<Stock> = emptyList(),
    val stockSort: StockSort = StockSort.BY_CODE_DESC,
    @StringRes val errorMessage: Int? = null,
    val transientMessage: String? = null,
    val selectedStock: Stock? = null,
    val isSortSheetVisible: Boolean = false,
)
