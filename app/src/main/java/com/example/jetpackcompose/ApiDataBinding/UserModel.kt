package com.example.jetpackcompose.ApiDataBinding

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserModel : Serializable {

    @SerializedName("isActive")
    val isActive: Boolean? = false

    @SerializedName("imageUrl")

    val imageUrl: String? = ""

    @SerializedName("name")
    var name: String? = ""
    @SerializedName("password")
    var password: String? = ""
    @SerializedName("role")

    val role: String? = ""
}
