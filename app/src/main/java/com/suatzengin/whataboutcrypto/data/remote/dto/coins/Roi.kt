package com.suatzengin.whataboutcrypto.data.remote.dto.coins

data class Roi(
    val times: Float = 0f,
    val currency: String? = null,
    val percentage: Float = 0f
)