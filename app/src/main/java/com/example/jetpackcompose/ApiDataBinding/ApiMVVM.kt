package com.example.jetpackcompose.ApiDataBinding

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackcompose.Rest.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiMVVM : ViewModel() {

    private val _users = MutableLiveData<List<UserModel>>()
    val users: LiveData<List<UserModel>> = _users

    var navigation:Navigation?=null
    var list: ArrayList<UserModel>? =null


    fun GetAPIData() {
        ApiClient.setBaseUrl().getapidata().enqueue(object : Callback<List<UserModel>> {
            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>
            ) {
                var list=response.body()
                if (response.isSuccessful && response.body() != null) {
                    _users.postValue(response.body())
                    Log.e("API", "SIZE = ${response.body()!!.size}")
                    navigation?.getData(list!!)

                    navigation?.Message("Successfully Fetched")
                }
            }

            override fun onFailure(
                call: Call<List<UserModel>>,
                t: Throwable
            ) {

            }

        })
    }
}