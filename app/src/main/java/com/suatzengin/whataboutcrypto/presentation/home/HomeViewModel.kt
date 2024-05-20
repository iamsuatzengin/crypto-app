package com.suatzengin.whataboutcrypto.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.whataboutcrypto.domain.usecase.CoinsAndTrendingUseCase
import com.suatzengin.whataboutcrypto.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinsAndTrendingUseCase: CoinsAndTrendingUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListUiState())
    val state: StateFlow<CoinListUiState>
        get() = _state

    fun getCoinsAndTrending() {
        coinsAndTrendingUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update { it.copy(message = result.message) }
                }

                Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            coinList = result.data.coins,
                            trendingCoinList = result.data.trendingCoins
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}
