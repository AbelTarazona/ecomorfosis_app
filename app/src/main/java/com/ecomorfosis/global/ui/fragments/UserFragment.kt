package com.ecomorfosis.global.ui.fragments

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ecomorfosis.global.databinding.FragmentUserBinding
import com.ecomorfosis.global.models.RegisterInformation
import com.ecomorfosis.global.utils.OnContinueListener
import com.ecomorfosis.global.utils.StateRegister
import java.util.*


class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var onNextListener: OnContinueListener

    companion object {
        @JvmStatic
        fun newInstance() = UserFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity = context as Activity
        try {
            onNextListener = activity as OnContinueListener
        } catch (c: ClassCastException) {
            throw ClassCastException("$activity must override")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button2.setOnClickListener {

            val nombre = binding.editText.text.toString()
            val cumple = binding.editText2.text.toString()
            val correo = binding.editText3.text.toString()

            if (nombre.isEmpty()) {
                Toast.makeText(context, "Ingrese un nombre", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (cumple.isEmpty()) {
                Toast.makeText(context, "Ingrese fecha de nacimiento", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (correo.isEmpty()) {
                Toast.makeText(context, "Ingrese un correo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!binding.cbTerms.isChecked) {
                Toast.makeText(
                    context,
                    "Debe aceptar los términos y condiciones",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val data = RegisterInformation(
                name = nombre,
                birth = cumple,
                mail = correo
            )
            onNextListener.onNext(StateRegister.USER, data)
        }

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        binding.editText2.setOnClickListener {
            val datePickerDialog =
                DatePickerDialog(
                    requireContext(), DatePickerDialog.OnDateSetListener
                    { _, year, monthOfYear, dayOfMonth ->
                        binding.editText2.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year)
                    }, year, month, day
                )
            datePickerDialog.show()
        }

    }

}