package com.suatzengin.whataboutcrypto.util

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
}