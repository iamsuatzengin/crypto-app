package com.suatzengin.whataboutcrypto.data.remote.dto.coins

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Roi(
    val times: Float = 0f,
    val currency: String? = null,
    val percentage: Float = 0f
): Parcelable
