package com.ecomorfosis.global.repository

import android.util.Log
import com.ecomorfosis.global.models.Question
import com.ecomorfosis.global.retrofit.EcomorfosisRetrofit
import com.ecomorfosis.global.retrofit.UserRequest
import com.ecomorfosis.global.utils.DataState
import com.ecomorfosis.global.utils.KeyConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by AbelTarazona on 30/11/2020
 */
class MainRepository(
    private val ecomorfosisRetrofit: EcomorfosisRetrofit
) {

    suspend fun getQuestions(): Flow<DataState<List<Question>>> = flow {
        emit(DataState.Loading)
        try {

            val response = ecomorfosisRetrofit.getQuestions()
            val questions = response.questions

            when (response.statusCode) {
                KeyConstants.StatusCode.SUCCESS -> {
                    emit(DataState.Success(questions))
                }
                else -> {
                    emit(DataState.ErrorMessage("Ocurrió un error"))
                }
            }


        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun createUser(request: UserRequest): Flow<DataState<String>> = flow {
        emit(DataState.Loading)
        try {
            val response = ecomorfosisRetrofit.createUser(request)

            when (response.statusCode) {
                KeyConstants.StatusCode.SUCCESS -> {
                    emit(DataState.Success(response.msg))
                }
                else -> {
                    emit(DataState.ErrorMessage("Ocurrió un error"))
                }
            }

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }


}