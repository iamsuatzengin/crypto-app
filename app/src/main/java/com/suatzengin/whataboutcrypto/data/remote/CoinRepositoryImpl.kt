package com.suatzengin.whataboutcrypto.data.remote

import com.suatzengin.whataboutcrypto.domain.model.HomeType
import com.suatzengin.whataboutcrypto.domain.repository.CoinRepository
import com.suatzengin.whataboutcrypto.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CoinRepository {
    override fun getCoinList(): Flow<Resource<List<HomeType>>> {

        return flow {
            emit(Resource.Loading())
            delay(1000)
            try {
                val coinList = apiService.getCoinList()

                val trendingCoins = apiService.getTrendingCoins().coins

                val list = listOf(
                    HomeType.CoinList(coinList),
                    HomeType.TrendingCoins(trendingCoins)
                )

                emit(Resource.Success(list))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.localizedMessage ?: "Http Exception"))
            }
        }
    }
}

