package com.ecomorfosis.global.models

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by AbelTarazona on 30/08/2020
 */
@Parcelize
data class Question(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("reason")
    @Expose
    val reason: String,
    @SerializedName("image")
    @Expose
    val image: String,
    @SerializedName("video")
    @Expose
    val video: String,
    @SerializedName("detail")
    @Expose
    val detail: String,
    @SerializedName("answers")
    @Expose
    val answers: String
) : Parcelable {

    fun getListAnswers(): List<Answer> {
        val gson = Gson()
        return gson.fromJson(answers, Array<Answer>::class.java).toList()
    }

}