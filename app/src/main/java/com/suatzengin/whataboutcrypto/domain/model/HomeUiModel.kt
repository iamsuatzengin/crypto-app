package com.suatzengin.whataboutcrypto.domain.model

import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinItem
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.TrendingCoin

data class HomeUiModel(
    val coins: List<CoinItem>,
    val trendingCoins: List<TrendingCoin>
)
