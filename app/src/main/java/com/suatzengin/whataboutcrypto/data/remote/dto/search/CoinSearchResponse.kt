package com.suatzengin.whataboutcrypto.data.remote.dto.search

import com.google.gson.annotations.SerializedName


data class CoinSearchResponse(
    val id: String,
    val name: String,
    val symbol: String,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int,
    @SerializedName("large")
    val imageUrl: String
)
