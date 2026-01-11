package com.example.jetpackcompose.Rest

import com.example.jetpackcompose.Model.UserData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/users/1")
    fun getData(): Call<UserData>
}

