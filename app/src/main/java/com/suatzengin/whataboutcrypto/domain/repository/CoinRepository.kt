package com.suatzengin.whataboutcrypto.domain.repository


import com.suatzengin.whataboutcrypto.domain.model.HomeType
import com.suatzengin.whataboutcrypto.util.Resource
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoinList(): Flow<Resource<List<HomeType>>>
}
