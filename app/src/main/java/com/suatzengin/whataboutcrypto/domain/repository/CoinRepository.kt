package com.suatzengin.whataboutcrypto.domain.repository


import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinItem
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinMarketChart
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.TrendingCoin
import com.suatzengin.whataboutcrypto.data.remote.dto.markets.Exchange
import com.suatzengin.whataboutcrypto.util.Resource
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getMarketList(): Flow<Resource<List<Exchange>>>
    fun getCoins(): Flow<List<CoinItem>>
    fun getTrendingCoins(): Flow<List<TrendingCoin>>

    fun getMarketChart(id: String): Flow<Resource<CoinMarketChart>>
}
