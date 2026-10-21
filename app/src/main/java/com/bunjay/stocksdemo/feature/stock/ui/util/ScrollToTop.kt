package com.bunjay.stocksdemo.feature.stock.ui.util

import androidx.compose.foundation.lazy.LazyListState

private const val SCROLL_TO_TOP_THRESHOLD = 10

private const val SCROLL_TO_TOP_JUMP_INDEX = 30

fun shouldShowScrollToTop(firstVisibleItemIndex: Int): Boolean =
    firstVisibleItemIndex >= SCROLL_TO_TOP_THRESHOLD

suspend fun LazyListState.scrollToTop() {
    if (firstVisibleItemIndex > SCROLL_TO_TOP_JUMP_INDEX) scrollToItem(SCROLL_TO_TOP_JUMP_INDEX)
    animateScrollToItem(0)
}
