package com.ecomorfosis.global.ui.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.ecomorfosis.global.R
import com.ecomorfosis.global.models.Question

/**
 * Created by AbelTarazona on 3/12/2020
 */
class DialogErrorTablero(
    private val question: Question,
    private val callback: Callback
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
        dialog.setContentView(R.layout.dialog_error_tablero)

        val detail = dialog.findViewById<TextView>(R.id.textView7)
        val video = dialog.findViewById<TextView>(R.id.tvVideo)
        val image = dialog.findViewById<TextView>(R.id.tvImagen)
        val reference = dialog.findViewById<TextView>(R.id.tvDetalle)

        detail.text = question.reason

        video.setOnClickListener {
            Toast.makeText(context, question.video, Toast.LENGTH_SHORT).show()
        }

        image.setOnClickListener {
            Toast.makeText(context, question.image, Toast.LENGTH_SHORT).show()
        }

        reference.setOnClickListener {
            Toast.makeText(context, question.detail, Toast.LENGTH_SHORT).show()
        }

        dialog.findViewById<Button>(R.id.btnStartGame).setOnClickListener {
            callback.incorrectAnswer()
            dialog.dismiss()
        }


    }

    override fun showDialog() {
        dialog.show()
    }

    interface Callback {
        fun incorrectAnswer()
    }

}