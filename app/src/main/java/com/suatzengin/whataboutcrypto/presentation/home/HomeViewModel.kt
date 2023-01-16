package com.suatzengin.whataboutcrypto.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.whataboutcrypto.domain.repository.CoinRepository
import com.suatzengin.whataboutcrypto.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
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
        getCoins()
    }

    fun getCoins(){
        viewModelScope.launch {
            try {
                println("get coins içi")
                _state.update { it.copy(isLoading = true) }
                val coinsDeferred = async { repository.getCoins() }
                val trendingCoinsDeferred = async { repository.getTrendingCoins() }

                val coins = coinsDeferred.await()
                val trending = trendingCoinsDeferred.await()
                _state.update {
                    it.copy(coinList = coins, trendingCoinList = trending)
                }
            }catch (e: Exception){
                _eventChannel.send(UiEvent.ShowMessage(e.localizedMessage ?: "Exception"))
            }
        }
    }

}