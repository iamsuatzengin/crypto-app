package com.suatzengin.whataboutcrypto.data.repository

import com.suatzengin.whataboutcrypto.data.remote.ApiService
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinDetail
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinMarketChart
import com.suatzengin.whataboutcrypto.data.remote.dto.markets.Exchange
import com.suatzengin.whataboutcrypto.data.remote.dto.search.Search
import com.suatzengin.whataboutcrypto.domain.repository.CoinRepository
import com.suatzengin.whataboutcrypto.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CoinRepository {

    override fun getCoins() = flow {
        emit(Resource.Loading())
        try {
            val coins = apiService.getCoinList()
            emit(Resource.Success(data = coins))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }

    override fun getTrendingCoins() = flow {
        emit(Resource.Loading())
        try {
            val coins = apiService.getTrendingCoins().coins
            emit(Resource.Success(data = coins))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }

    override fun getMarketChart(id: String, day: Int): Flow<Resource<CoinMarketChart>> = flow {
        emit(Resource.Loading())
        try {
            val marketCharts = apiService.getMarketCharts(id, days = day)
            emit(Resource.Success(marketCharts))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }

    override fun getCoinDetail(id: String): Flow<Resource<CoinDetail>> = flow {
        emit(Resource.Loading())
        try {
            val coinDetail = apiService.getCoinDetail(id)
            emit(Resource.Success(data = coinDetail))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }

    override fun getMarketList(): Flow<Resource<List<Exchange>>> = flow {
        emit(Resource.Loading())
        try {
            val marketList = apiService.getMarketList()
            emit(Resource.Success(marketList))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
        }
    }

    override fun search(query: String): Flow<Resource<Search>> {
        return flow {
            try {
                emit(Resource.Loading())
                val searchQuery = apiService.search(query = query)
                emit(Resource.Success(searchQuery))
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: "An error occurred!"))
            }
        }
    }
}

