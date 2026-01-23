package com.example.jetpackcompose.Rest

import com.example.customapiwithmvvm.simpleMVVM.JetpackComposeMVVM.ComposeModel
import com.example.jetpackcompose.Model.UserData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/users/1")
    fun getData(): Call<UserData>


    @GET("/users/2")
    fun getComposeData(): Call<ComposeModel>
}

