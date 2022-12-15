package com.suatzengin.whataboutcrypto.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.whataboutcrypto.domain.repository.CoinRepository
import com.suatzengin.whataboutcrypto.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListUiState())
    val state: StateFlow<CoinListUiState>
        get() = _state

    private val _eventChannel = Channel<UiEvent>(Channel.BUFFERED)
    val eventFlow = _eventChannel.receiveAsFlow()

    init {
        _state.update { it.copy(isLoading = true) }
        getCoinList()
        getTrendingCoins()
    }

    private fun getCoinList(){
        viewModelScope.launch {
            try {
                repository.getCoins().collect{ list ->
                    _state.update {
                        it.copy(coinList = list, isLoading = false)
                    }
                }
            }catch (e: Exception){
                _eventChannel.send(
                    UiEvent.ShowSnackbar(
                        message = e.localizedMessage ?: "Unknown exception!"
                    )
                )
            }
        }
    }

    private fun getTrendingCoins(){
        viewModelScope.launch {
            try {
                repository.getTrendingCoins().collect{ list ->
                    _state.update {
                        it.copy(trendingCoinList = list, isLoading = false)
                    }
                }
            }catch (e: Exception){
                _eventChannel.send(
                    UiEvent.ShowSnackbar(
                        message = e.localizedMessage ?: "Unknown exception!"
                    )
                )
            }
        }
    }
}