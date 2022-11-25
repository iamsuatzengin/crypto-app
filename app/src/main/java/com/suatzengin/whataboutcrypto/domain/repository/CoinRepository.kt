package com.suatzengin.whataboutcrypto.domain.repository


import com.suatzengin.whataboutcrypto.data.remote.dto.markets.Exchange
import com.suatzengin.whataboutcrypto.domain.model.HomeType
import com.suatzengin.whataboutcrypto.util.Resource
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoinList(): Flow<Resource<List<HomeType>>>

    fun getMarketList(): Flow<Resource<List<Exchange>>>
}
