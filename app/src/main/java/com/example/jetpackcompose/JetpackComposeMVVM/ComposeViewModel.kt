package com.example.customapiwithmvvm.simpleMVVM.JetpackComposeMVVM

import androidx.lifecycle.ViewModel
import com.example.jetpackcompose.Rest.ApiClient
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComposeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UserUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<UiEvent>()
    val event = _event.asSharedFlow()

    val viewModelScope: ViewModel? = null

    fun GetComposeViewData() {
        ApiClient.setBaseUrl().getComposeData().enqueue(object : Callback<ComposeModel> {
            override fun onResponse(call: Call<ComposeModel?>, response: Response<ComposeModel?>) {
                var res = response.body()
                if (res != null) {
                    _uiState.value = UserUiState(data = res)

                    //viewModelScope.launch {
                    _event.tryEmit(UiEvent.ShowToast("Successfully Fetched"))
                    // }


                    /*
                    _event.emit() is use for the viewmodelscope (Coroutines) if we want to use it in

                     the viewmodel ,we must use viewmodelscope.launch{
                      _event.emit(UiEvent.ShowToast("Successfully Fetched"))
                    }

                    Directly use tryEmit with out Coroutines
                    _event.tryEmit(UiEvent.ShowToast("Successfully Fetched"))

                     */
                }
            }

            override fun onFailure(
                call: Call<ComposeModel?>, t: Throwable
            ) {

            }

        })

    }

}