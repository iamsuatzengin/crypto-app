package com.suatzengin.whataboutcrypto.domain.usecase

import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinItem
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.TrendingCoin
import com.suatzengin.whataboutcrypto.domain.model.HomeUiModel
import com.suatzengin.whataboutcrypto.domain.repository.CoinRepository
import com.suatzengin.whataboutcrypto.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CoinsAndTrendingUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<HomeUiModel>> =
        combine<List<CoinItem>, List<TrendingCoin>, Resource<HomeUiModel>>(
            flow = repository.getCoins(),
            flow2 = repository.getTrendingCoins()
        ) { coins, trendingCoins ->
            Resource.Success(
                data = HomeUiModel(
                    coins = coins,
                    trendingCoins = trendingCoins
                )
            )
        }.onStart {
            emit(Resource.Loading)
        }.catch {
            emit(Resource.Error(message = it.localizedMessage ?: "An error occurred!"))
        }
}

