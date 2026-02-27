package com.example.jetpackcompose.Rest

import com.example.customapiwithmvvm.simpleMVVM.JetpackComposeMVVM.ComposeModel
import com.example.jetpackcompose.ApiDataBinding.ObjectDataBinding.Data1
import com.example.jetpackcompose.ApiDataBinding.UserModel
import com.example.jetpackcompose.Model.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("/users/1")
    fun getData(): Call<UserData>

    @GET("users/1")
    fun objectBinding(): Call<Data1>

    @GET("/users/2")
    fun getComposeData(): Call<ComposeModel>

    @GET("api/users")
    fun getapidata():Call<List<UserModel>>

    @GET("users/2")
    fun getUser():Call<GetData>

    @POST("users")
     fun saveUser(@Body request: SaveDataRequest): Call<SaveDataResponse>

}

