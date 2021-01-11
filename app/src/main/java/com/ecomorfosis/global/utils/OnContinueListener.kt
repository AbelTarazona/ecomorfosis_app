package com.ecomorfosis.global.utils

import com.ecomorfosis.global.models.RegisterInformation

/**
 * Created by AbelTarazona on 29/11/2020
 */
interface OnContinueListener {
    fun onNext(state: StateRegister, data: RegisterInformation)
}