package com.example.jetpackcompose.Rest

import android.os.Parcel
import android.os.Parcelable
import com.example.jetpackcompose.Model.UserData
import com.google.gson.annotations.SerializedName

data class GetData(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: UserData?
)

