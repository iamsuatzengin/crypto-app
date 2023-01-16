package com.suatzengin.whataboutcrypto.util

sealed class UiEvent {
    data class ShowMessage(val message: String) : UiEvent()
}