package com.ecomorfosis.global.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by AbelTarazona on 5/12/2020
 */
data class UserRequest(
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("birth")
    @Expose
    val birth: String,
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("country")
    @Expose
    val country: String,
)