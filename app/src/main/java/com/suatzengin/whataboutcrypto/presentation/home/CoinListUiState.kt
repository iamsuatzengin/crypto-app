package com.suatzengin.whataboutcrypto.presentation.home

import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinItem
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.TrendingCoin


data class CoinListUiState(
    val coinList: List<CoinItem> = emptyList(),
    val trendingCoinList: List<TrendingCoin> = emptyList(),
    val isLoading: Boolean = false,
    val message: String = ""
)