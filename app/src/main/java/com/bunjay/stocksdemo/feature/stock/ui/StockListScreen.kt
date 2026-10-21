package com.bunjay.stocksdemo.feature.stock.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bunjay.stocksdemo.R
import com.bunjay.stocksdemo.app.ui.theme.ThemeMode
import com.bunjay.stocksdemo.feature.stock.domain.model.Stock
import com.bunjay.stocksdemo.feature.stock.domain.model.StockSort
import com.bunjay.stocksdemo.feature.stock.ui.component.SortBottomSheet
import com.bunjay.stocksdemo.feature.stock.ui.component.StockCard
import com.bunjay.stocksdemo.feature.stock.ui.component.StockInfoDialog
import com.bunjay.stocksdemo.feature.stock.ui.component.ThemeToggleButton
import com.bunjay.stocksdemo.feature.stock.ui.state.StockListUiState
import com.bunjay.stocksdemo.feature.stock.ui.util.scrollToTop
import com.bunjay.stocksdemo.feature.stock.ui.util.shouldShowScrollToTop
import kotlinx.coroutines.launch

@Composable
fun StockListScreen(
    themeMode: ThemeMode,
    onToggleTheme: () -> Unit,
    viewModel: StockListViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    StockListContent(
        state = state,
        themeMode = themeMode,
        onToggleTheme = onToggleTheme,
        onRefresh = viewModel::onRefresh,
        onRetry = viewModel::onRetry,
        onSortClick = viewModel::onSortClick,
        onSortSelected = viewModel::onSortSelected,
        onSortSheetDismiss = viewModel::onSortSheetDismiss,
        onStockClick = viewModel::onStockClick,
        onDialogDismiss = viewModel::onDialogDismiss,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockListContent(
    state: StockListUiState,
    themeMode: ThemeMode,
    onToggleTheme: () -> Unit,
    onRefresh: () -> Unit,
    onRetry: () -> Unit,
    onSortClick: () -> Unit,
    onSortSelected: (StockSort) -> Unit,
    onSortSheetDismiss: () -> Unit,
    onStockClick: (Stock) -> Unit,
    onDialogDismiss: () -> Unit,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val isListShown = !state.isLoading && state.errorMessage == null
    val showScrollToTop by remember {
        derivedStateOf { shouldShowScrollToTop(listState.firstVisibleItemIndex) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.stock_list_title)) },
                actions = {
                    ThemeToggleButton(themeMode = themeMode, onClick = onToggleTheme)
                    IconButton(onClick = onSortClick) {
                        Icon(Icons.AutoMirrored.Filled.Sort, contentDescription = stringResource(R.string.action_sort))
                    }
                },
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = isListShown && showScrollToTop,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut(),
            ) {
                FloatingActionButton(onClick = { coroutineScope.launch { listState.scrollToTop() } }) {
                    Icon(Icons.Filled.KeyboardArrowUp, contentDescription = null)
                }
            }
        },
    ) { innerPadding ->
        val contentModifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        when {
            state.isLoading -> Box(contentModifier, contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

            state.errorMessage != null -> ErrorContent(stringResource(state.errorMessage), onRetry, contentModifier)

            else -> PullToRefreshBox(
                isRefreshing = state.isRefreshing,
                onRefresh = onRefresh,
                modifier = contentModifier,
            ) {
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(state.stocks, key = { it.code }) { stock ->
                        StockCard(stock = stock, onClick = { onStockClick(stock) })
                    }
                }
            }
        }
    }

    if (state.isSortSheetVisible) {
        SortBottomSheet(selected = state.stockSort, onSelect = onSortSelected, onDismiss = onSortSheetDismiss)
    }
    state.selectedStock?.let { StockInfoDialog(stock = it, onDismiss = onDialogDismiss) }
}

@Composable
private fun ErrorContent(message: String, onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(message, style = MaterialTheme.typography.bodyLarge)
        Button(onClick = onRetry) { Text(stringResource(R.string.action_retry)) }
    }
}