package com.ecomorfosis.global.utils

import java.lang.Exception

/**
 * Created by AbelTarazona on 30/11/2020
 */
sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    data class ErrorMessage(val msg: String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()

}