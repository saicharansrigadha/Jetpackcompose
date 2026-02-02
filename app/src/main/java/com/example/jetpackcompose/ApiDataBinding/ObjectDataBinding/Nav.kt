package com.example.jetpackcompose.ApiDataBinding.ObjectDataBinding

import com.example.jetpackcompose.ApiDataBinding.UserModel

interface Nav {
    fun getData(data: Data1?)

    fun getDataList(list: List<UserModel>)

    fun Message(string: String)
}