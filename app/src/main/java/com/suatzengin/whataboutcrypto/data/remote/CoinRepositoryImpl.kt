package com.suatzengin.whataboutcrypto.data.remote

import com.suatzengin.whataboutcrypto.data.remote.dto.CoinItem
import com.suatzengin.whataboutcrypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): CoinRepository{
    override fun getCoinList(): Flow<List<CoinItem>> {
        return flow {
            val list = apiService.getCoinList()
            emit(list)
        }
    }

}