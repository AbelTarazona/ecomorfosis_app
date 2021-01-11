package com.ecomorfosis.global.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by AbelTarazona on 5/12/2020
 */
open class BaseResponse {
    @SerializedName("statusCode")
    @Expose
    var statusCode: Int = -1

    @SerializedName("msg")
    @Expose
    lateinit var msg: String
}