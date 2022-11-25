package com.suatzengin.whataboutcrypto.presentation.markets

import com.suatzengin.whataboutcrypto.data.remote.dto.markets.Exchange

data class MarketListUiState(
    val list: List<Exchange> = emptyList(),
    val isLoading: Boolean = false
)
