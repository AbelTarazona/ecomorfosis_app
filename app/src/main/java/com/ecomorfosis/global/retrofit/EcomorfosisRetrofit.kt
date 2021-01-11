package com.ecomorfosis.global.retrofit

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by AbelTarazona on 30/11/2020
 */
interface EcomorfosisRetrofit {

    @GET("questions")
    suspend fun getQuestions(): QuestionsResponse

    @POST("users/create")
    suspend fun createUser(@Body request: UserRequest): BaseResponse

}