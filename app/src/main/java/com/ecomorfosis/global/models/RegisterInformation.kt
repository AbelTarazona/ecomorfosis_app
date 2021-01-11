package com.ecomorfosis.global.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by AbelTarazona on 29/11/2020
 */
@Parcelize
data class RegisterInformation(
    var name: String = "",
    var birth: String = "",
    var mail: String = "",
    var country: String = ""
) : Parcelable