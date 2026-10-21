package com.bunjay.stocksdemo.feature.stock.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bunjay.stocksdemo.R
import com.bunjay.stocksdemo.feature.stock.domain.model.Stock
import com.bunjay.stocksdemo.feature.stock.domain.model.StockSort
import com.bunjay.stocksdemo.feature.stock.domain.usecase.GetStocksUseCase
import com.bunjay.stocksdemo.feature.stock.domain.usecase.SortStocksUseCase
import com.bunjay.stocksdemo.feature.stock.ui.state.StockListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(
    private val getStocks: GetStocksUseCase,
    private val sortStocks: SortStocksUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(StockListUiState())
    val uiState: StateFlow<StockListUiState> = _uiState.asStateFlow()

    /** Unsorted list as fetched, so changing the sort order never needs a refetch. */
    private var rawStocks: List<Stock> = emptyList()
    private var loadJob: Job? = null

    init {
        load()
    }

    fun onRetry() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        load()
    }

    fun onRefresh() {
        _uiState.update { it.copy(isRefreshing = true) }
        load()
    }

    fun onSortClick() = _uiState.update { it.copy(isSortSheetVisible = true) }

    fun onSortSheetDismiss() = _uiState.update { it.copy(isSortSheetVisible = false) }

    fun onSortSelected(sort: StockSort) = _uiState.update {
        it.copy(stockSort = sort, stocks = sortStocks(rawStocks, sort), isSortSheetVisible = false)
    }

    fun onStockClick(stock: Stock) = _uiState.update { it.copy(selectedStock = stock) }

    fun onDialogDismiss() = _uiState.update { it.copy(selectedStock = null) }

    fun onTransientMessageShown() = _uiState.update { it.copy(transientMessage = null) }

    private fun load() {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            getStocks()
                .onSuccess { stocks ->
                    rawStocks = stocks
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isRefreshing = false,
                            errorMessage = null,
                            stocks = sortStocks(stocks, it.stockSort),
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = error.toUserMessage())
                    }
                }
        }
    }
}

internal fun Throwable.toUserMessage(): Int = when (this) {
    is IOException -> R.string.error_network
    else -> R.string.error_load_failed
}
