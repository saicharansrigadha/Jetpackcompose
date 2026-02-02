package com.example.jetpackcompose.ApiDataBinding.ObjectDataBinding

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Data1: Serializable {
    @SerializedName("firstName")
    var firstName: String?=""

    @SerializedName("maidenName")
    var maidenName:String?=""
}