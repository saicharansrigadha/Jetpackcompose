package com.example.jetpackcompose.ApiDataBinding.ObjectDataBinding

import com.example.jetpackcompose.ApiDataBinding.Navigation
import com.example.jetpackcompose.ApiDataBinding.UserModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackcompose.Rest.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ObjectMVVM : ViewModel() {

    private val _users = MutableLiveData<List<UserModel>>()
    val users: LiveData<List<UserModel>> = _users            //List


    private val _userobj= MutableLiveData<Data1>()
    val userobj:LiveData<Data1> = _userobj                 //Object

    var navigation:Nav?=null
    //var list: ArrayList<UserModel>? =null


    fun GetAPIData() {
        ApiClient.setBaseUrl().objectBinding().enqueue(object : Callback<Data1> {
            override fun onResponse(
                call: Call<Data1>,
                response: Response<Data1>
            ) {
                if (response.isSuccessful && response.body() != null) {

                    navigation?.getData(response.body())

                    navigation?.getData(_userobj.value)            //we can send the live data value to interface using like this

                                                                         //_userobj.value=response.body() then navigation?.getData(_userobj.value)

                    _userobj.value=response.body() //this is livedata way storing response in the value

                    navigation?.Message("Successfully Fetched")
                }
            }

            override fun onFailure(
                call: Call<Data1>,
                t: Throwable
            ) {

            }

        })
    }

  //LIST


    fun getListData() {
        ApiClient.setBaseUrl().getapidata().enqueue(object : Callback<List<UserModel>> {
            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>
            )
                {
                    val list = response.body()
                    if (response.isSuccessful && list != null) {
                       _users.value = list
                        navigation?.getDataList(list)   // no cast
                        navigation?.Message("Fetched list")
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