package com.suatzengin.whataboutcrypto.data.remote

import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinItem
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.Trending
import com.suatzengin.whataboutcrypto.data.remote.dto.markets.Exchange
import com.suatzengin.whataboutcrypto.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.COIN_LIST_URL)
    suspend fun getCoinList(): List<CoinItem>

    @GET(Constants.TRENDING_URL)
    suspend fun getTrendingCoins(): Trending

    @GET(Constants.MARKETS_URL)
    suspend fun getMarketList(
        @Query("per_page") perPage: Int = 50,
        @Query("page") page: Int = 1
    ): List<Exchange>
}