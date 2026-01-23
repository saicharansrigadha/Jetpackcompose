package com.example.customapiwithmvvm.simpleMVVM.JetpackComposeMVVM


data class UserUiState(
    val isLoading: Boolean = false,
    val data: ComposeModel? = null,
    val error: String? = null
)
