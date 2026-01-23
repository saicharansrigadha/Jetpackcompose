package com.example.customapiwithmvvm.simpleMVVM.JetpackComposeMVVM

sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
}
