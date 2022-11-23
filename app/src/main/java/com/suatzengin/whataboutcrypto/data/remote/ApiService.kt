package com.suatzengin.whataboutcrypto.data.remote

import com.suatzengin.whataboutcrypto.data.remote.dto.CoinItem
import com.suatzengin.whataboutcrypto.util.Constants
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.COIN_LIST_URL)
    suspend fun getCoinList(): List<CoinItem>
}