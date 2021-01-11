package com.ecomorfosis.global.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by AbelTarazona on 5/12/2020
 */

@Parcelize
data class QuestionScreen(
    val id: Int = 0,
    val name: String,
    val question: Question
) : Parcelable