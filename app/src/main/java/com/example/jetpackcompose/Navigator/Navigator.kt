package com.example.jetpackcompose.Navigator

import com.example.jetpackcompose.Model.UserData

interface Navigator {

    fun getData(data: UserData)
    fun message()
}