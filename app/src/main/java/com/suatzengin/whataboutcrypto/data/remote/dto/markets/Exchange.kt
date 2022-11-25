package com.suatzengin.whataboutcrypto.data.remote.dto.markets

import com.google.gson.annotations.SerializedName

data class Exchange(
    val id: String,
    val name: String,
    @SerializedName("year_established")
    val yearEstablished: Long? = null,
    val country: String? = null,
    val description: String? = null,
    val url: String? = null,
    val image: String? = null,
    @SerializedName("has_trading_incentive")
    val hasTradingIncentive: Boolean = false,
    @SerializedName("trust_score")
    val trustScore: Int,
    @SerializedName("trust_score_rank")
    val trustScoreRank: Long = 0,
    @SerializedName("trade_volume_24h_btc")
    val tradeVolume24hBtc: Double = 0.0,
    @SerializedName("trade_volume_24h_btc_normalized")
    val tradeVolume24hBtcNormalized: Double = 0.0,
)

