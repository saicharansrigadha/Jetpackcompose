package com.example.jetpackcompose.LazyColumn

import java.io.Serializable

data class Model(

    var name: String? = "",
    var isSelected: Boolean = false


) : Serializable