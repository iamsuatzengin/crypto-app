package com.suatzengin.whataboutcrypto.presentation.search

import com.suatzengin.whataboutcrypto.data.remote.dto.search.CoinSearchResponse


data class SearchUiState(
    val list: List<CoinSearchResponse> = emptyList(),
    val message: String = "",
    val isLoading: Boolean = false
)