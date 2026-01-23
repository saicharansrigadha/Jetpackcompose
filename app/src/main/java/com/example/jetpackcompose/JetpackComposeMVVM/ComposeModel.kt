package com.example.customapiwithmvvm.simpleMVVM.JetpackComposeMVVM

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ComposeModel : Serializable {
    @SerializedName("id")
    val id: Int? = null

    @SerializedName("firstName")
    val firstName: String? = null

    @SerializedName("lastName")
    val lastName: String? = null

    @SerializedName("email")
    val email: String? = null

    @SerializedName("phone")
    val phone: String? = null


}