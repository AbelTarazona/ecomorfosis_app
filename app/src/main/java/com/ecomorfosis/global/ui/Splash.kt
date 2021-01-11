package com.ecomorfosis.global.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ecomorfosis.global.utils.launchActivity

/**
 * Created by AbelTarazona on 6/12/2020
 */
class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goMain()
    }

    private fun goMain() {
        launchActivity<MainActivity> {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        finish()
    }
}