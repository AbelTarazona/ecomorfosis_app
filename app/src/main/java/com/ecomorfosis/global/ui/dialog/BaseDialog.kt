package com.ecomorfosis.global.ui.dialog

import android.app.Dialog
import android.content.Context

/**
 * Created by AbelTarazona on 23/10/2020
 */
abstract class BaseDialog {

    protected lateinit var dialog: Dialog

    abstract fun init(context: Context)

    abstract fun showDialog()

}