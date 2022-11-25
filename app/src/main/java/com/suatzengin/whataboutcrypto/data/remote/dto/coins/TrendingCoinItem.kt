package com.suatzengin.whataboutcrypto.data.remote.dto.coins

import com.google.gson.annotations.SerializedName

data class TrendingCoinItem(
    val id: String,
    @SerializedName("coin_id")
    val coinId: Int,
    val name: String,
    val symbol: String,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int,
    val thumb: String,
    val small: String,
    val large: String,
    val slug: String,
    @SerializedName("price_btc")
    val priceBtc: Double,
    val score: Int
)