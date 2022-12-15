package com.suatzengin.whataboutcrypto.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.whataboutcrypto.domain.repository.CoinRepository
import com.suatzengin.whataboutcrypto.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: CoinRepository
): ViewModel() {

    private val _statePriceList = MutableStateFlow<List<List<Double>>>(emptyList())
    val statePriceList: StateFlow<List<List<Double>>>
        get() = _statePriceList


    fun getAllData(id: String) {
        repository.getMarketChart(id).onEach{ result ->
            when(result){
                is Resource.Success -> {
                    _statePriceList.value = result.data?.prices ?: emptyList()
                }
                is Resource.Loading -> {}
                is Resource.Error -> {}
            }
        }.launchIn(viewModelScope)
    }

}