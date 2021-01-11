package com.ecomorfosis.global.ui.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.ecomorfosis.global.R

/**
 * Created by AbelTarazona on 3/12/2020
 */
class DialogExitTablero(
    val callback: Callback
) : BaseDialog() {
    override fun init(context: Context) {
        dialog = Dialog(context)
        val window = dialog.window
        if (window != null) {
            window.decorView.setBackgroundResource(android.R.color.transparent)
            window.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.setCancelable(false)

            val wlp = window.attributes
            wlp.gravity = Gravity.CENTER
            window.attributes = wlp

            window.setWindowAnimations(R.style.DialogAnimation)
        }
        dialog.setContentView(R.layout.dialog_exit_tablero)

        dialog.findViewById<Button>(R.id.button3).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<TextView>(R.id.textView9).setOnClickListener {
            dialog.dismiss()
            callback.onCloseGame()
        }

    }

    override fun showDialog() {
        dialog.show()
    }

    interface Callback {
        fun onCloseGame()
    }
}