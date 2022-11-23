package com.suatzengin.whataboutcrypto.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.whataboutcrypto.data.remote.dto.CoinItem
import com.suatzengin.whataboutcrypto.domain.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListUiState())
    val state: StateFlow<CoinListUiState>
        get() = _state

    init {
        repository.getCoinList().onEach { result ->
            _state.value = CoinListUiState(list = result)
        }.launchIn(viewModelScope)
    }
}

data class CoinListUiState(
    val list: List<CoinItem> = emptyList()
)