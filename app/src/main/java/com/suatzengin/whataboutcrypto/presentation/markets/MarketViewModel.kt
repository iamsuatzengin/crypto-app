package com.suatzengin.whataboutcrypto.presentation.markets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.whataboutcrypto.domain.repository.CoinRepository
import com.suatzengin.whataboutcrypto.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MarketListUiState())
    val state: StateFlow<MarketListUiState>
        get() = _state

    init {
        getMarketList()
    }

    private fun getMarketList() {
        coinRepository.getMarketList().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(list = result.data ?: emptyList(), isLoading = false)
                    }
                }
                is Resource.Loading -> {
                    _state.update {
                        it.copy(list = emptyList(), isLoading = true)
                    }
                }
                is Resource.Error -> {
                    _state.update { it.copy(message = result.message ?: "Error!") }
                }
            }
        }.launchIn(viewModelScope)
    }
}