package com.ecomorfosis.global.retrofit

import com.ecomorfosis.global.models.Question
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by AbelTarazona on 30/11/2020
 */
data class QuestionsResponse(
    @SerializedName("questions")
    @Expose
    val questions: List<Question>
) : BaseResponse()