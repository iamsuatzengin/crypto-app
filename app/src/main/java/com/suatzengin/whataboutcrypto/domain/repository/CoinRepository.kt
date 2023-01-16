package com.suatzengin.whataboutcrypto.domain.repository


import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinItem
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinMarketChart
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.TrendingCoin
import com.suatzengin.whataboutcrypto.data.remote.dto.markets.Exchange
import com.suatzengin.whataboutcrypto.util.Resource
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getMarketList(): Flow<Resource<List<Exchange>>>
    suspend fun getCoins(): List<CoinItem>
    suspend fun getTrendingCoins(): List<TrendingCoin>

    fun getMarketChart(id: String, day: Int): Flow<Resource<CoinMarketChart>>
}
