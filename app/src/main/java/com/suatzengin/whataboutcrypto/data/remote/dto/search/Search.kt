package com.suatzengin.whataboutcrypto.data.remote.dto.search

data class Search(
    val coins: List<CoinSearchResponse>,
    //val exchanges: List<ExchangeSearchResponse>
)

//data class ExchangeSearchResponse(
//    val id: String,
//    val name: String,
//    @SerializedName("large")
//    val imageUrl: String
//)