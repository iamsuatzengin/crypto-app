package com.suatzengin.whataboutcrypto.data.remote

import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinDetail
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinItem
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinMarketChart
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.Trending
import com.suatzengin.whataboutcrypto.data.remote.dto.markets.Exchange
import com.suatzengin.whataboutcrypto.data.remote.dto.search.Search
import com.suatzengin.whataboutcrypto.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.COIN_LIST_URL)
    suspend fun getCoinList(): List<CoinItem>

    @GET(Constants.TRENDING_URL)
    suspend fun getTrendingCoins(): Trending

    @GET(Constants.MARKETS_URL)
    suspend fun getMarketList(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 50
    ): List<Exchange>

    @GET(Constants.MARKET_CHARTS)
    suspend fun getMarketCharts(
        @Path("id") id: String,
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("days") days: Int = 7,
        @Query("interval") interval: String = "daily"
    ): CoinMarketChart

    @GET(Constants.COIN_DETAIL)
    suspend fun getCoinDetail(
        @Path("id") id: String
    ): CoinDetail

    @GET(Constants.SEARCH)
    suspend fun search(
        @Query("query") query: String
    ): Search
}