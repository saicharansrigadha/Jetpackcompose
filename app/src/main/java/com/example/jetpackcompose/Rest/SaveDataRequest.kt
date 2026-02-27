package com.example.jetpackcompose.Rest

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SaveDataRequest : Serializable {
    @SerializedName("id")
    val id: Int? = null

    @SerializedName("firstName")
    var firstName: String? = null

    @SerializedName("lastName")
    var lastName: String? = null

    @SerializedName("email")
    val email: String? = null

    @SerializedName("phone")
    val phone: String? = null
}