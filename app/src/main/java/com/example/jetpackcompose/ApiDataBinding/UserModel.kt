package com.example.jetpackcompose.ApiDataBinding

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserModel : Serializable {

    @SerializedName("isActive")
    val isActive: Boolean? = false

    @SerializedName("imageUrl")

    val imageUrl: String? = ""

    @SerializedName("name")

    val name: String? = ""

    @SerializedName("role")

    val role: String? = ""
}
