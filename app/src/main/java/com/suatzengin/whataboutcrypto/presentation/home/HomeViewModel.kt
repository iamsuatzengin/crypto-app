package com.suatzengin.whataboutcrypto.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.whataboutcrypto.domain.repository.CoinRepository
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
    private val repository: CoinRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListUiState())
    val state: StateFlow<CoinListUiState>
        get() = _state

    init {
        getCoins()
        getTrendingCoins()
    }

    fun getCoins() {
        repository.getCoins().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.update { it.copy(coinList = result.data ?: emptyList()) }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(message = result.message ?: "Error!") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getTrendingCoins(){
        repository.getTrendingCoins().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.update { it.copy(trendingCoinList = result.data ?: emptyList()) }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(message = result.message ?: "Error!") }
                }
            }
        }.launchIn(viewModelScope)
    }
}