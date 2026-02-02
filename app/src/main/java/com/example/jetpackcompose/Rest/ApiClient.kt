package com.example.jetpackcompose.Rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var apiService: ApiInterface? = null

    //private var BASE_URL = "https://dummyjson.com/"


    private var BASE_URL="https://jetpack.free.beeceptor.com/"

    // This method sets the base URL and returns the ApiInterface
    fun setBaseUrl(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create<ApiInterface?>(ApiInterface::class.java)
        return apiService!!
    }

    // This method returns the ApiInterface if already created
    fun getApiService(): ApiInterface {
        return apiService!!
    }
}