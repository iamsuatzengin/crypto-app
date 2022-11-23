package com.suatzengin.whataboutcrypto.domain.repository

import com.suatzengin.whataboutcrypto.data.remote.dto.CoinItem
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoinList(): Flow<List<CoinItem>>
}