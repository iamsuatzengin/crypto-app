package com.suatzengin.whataboutcrypto.presentation.detail

import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinDetail
import com.suatzengin.whataboutcrypto.domain.model.CoinChartTimeSpan

data class DetailState(
    val priceList: List<List<Double>> = emptyList(),
    val timeRange: CoinChartTimeSpan = CoinChartTimeSpan.TIMESPAN_7DAYS,
    val coinDetail: CoinDetail? = null
)
