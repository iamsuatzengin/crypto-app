package com.suatzengin.whataboutcrypto.presentation.home

import com.suatzengin.whataboutcrypto.domain.model.HomeType

data class CoinListUiState(
    val list: List<HomeType> = emptyList(),
    val isLoading: Boolean = false
)