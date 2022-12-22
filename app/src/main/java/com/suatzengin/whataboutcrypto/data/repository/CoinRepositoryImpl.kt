package com.suatzengin.whataboutcrypto.data.repository

import com.suatzengin.whataboutcrypto.data.remote.ApiService
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinMarketChart
import com.suatzengin.whataboutcrypto.data.remote.dto.markets.Exchange
import com.suatzengin.whataboutcrypto.domain.repository.CoinRepository
import com.suatzengin.whataboutcrypto.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CoinRepository {

    override fun getCoins() = flow {
        emit(apiService.getCoinList())
    }
    override fun getTrendingCoins() = flow{
        emit(apiService.getTrendingCoins().coins)
    }

    override fun getMarketChart(id: String, day: Int): Flow<Resource<CoinMarketChart>> = flow {
        emit(Resource.Loading())
        try {
            val marketCharts = apiService.getMarketCharts(id, days = day)
            emit(Resource.Success(marketCharts))
        }catch (e: Exception){
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }

    override fun getMarketList(): Flow<Resource<List<Exchange>>> = flow {
        emit(Resource.Loading())
        delay(1000)
        try {
            val marketList = apiService.getMarketList()
            emit(Resource.Success(marketList))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }

}

