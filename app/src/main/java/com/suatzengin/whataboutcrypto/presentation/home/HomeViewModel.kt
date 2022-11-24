package com.suatzengin.whataboutcrypto.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.whataboutcrypto.domain.model.HomeType
import com.suatzengin.whataboutcrypto.domain.repository.CoinRepository
import com.suatzengin.whataboutcrypto.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
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
        getHomeData()
    }

    private fun getHomeData() {
        repository.getCoinList().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(list = result.data ?: emptyList(), isLoading = false)
                    }

                }
                is Resource.Error -> {
                    _eventChannel.send(
                        UiEvent.ShowToast(
                            message = result.message ?: "Unknown exception!"
                        )
                    )
                }
                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            list = emptyList(),
                            isLoading = true
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}


data class CoinListUiState(
//    val coinList: List<CoinItem> = emptyList(),
//    val trendingList: List<TrendingCoinItem> = emptyList(),
    val list: List<HomeType> = emptyList(),
    val isLoading: Boolean = false
)

sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
}