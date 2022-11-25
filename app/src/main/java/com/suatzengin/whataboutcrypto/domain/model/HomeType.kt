package com.suatzengin.whataboutcrypto.domain.model

import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinItem
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.TrendingCoin

sealed class HomeType{
    data class TrendingCoins(val coins: List<TrendingCoin>): HomeType()
    data class CoinList(val coins: List<CoinItem>): HomeType()
}
