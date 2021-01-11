package com.ecomorfosis.global.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ecomorfosis.global.R
import com.ecomorfosis.global.databinding.ActivityGameHomeBinding
import com.ecomorfosis.global.models.Answer
import com.ecomorfosis.global.ui.adapters.PlayersAdapter
import com.ecomorfosis.global.utils.KeyConstants
import com.ecomorfosis.global.utils.launchActivity
import com.ecomorfosis.global.utils.statusBarColorNew
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import java.util.ArrayList

class GameHomeActivity : AppCompatActivity(), PlayersAdapter.Callback {


    private var players = mutableListOf<String>()
    private val adapter: PlayersAdapter = PlayersAdapter(players, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        statusBarColorNew(R.color.background_card_question)

        binding.rvPlayers.adapter = adapter

        binding.btnAddPlayer.setOnClickListener {
            val name = binding.etPlayer.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(this, "Ingrese un nombre", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (players.contains(name)) {
                Toast.makeText(this, "Nombre ya ingresado", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            players.add(name)
            adapter.notifyDataSetChanged()
            binding.etPlayer.text?.clear()

        }

        binding.btnStartGame.setOnClickListener {
            if (!players.isNullOrEmpty()) {
                launchActivity<SessionGameActivity>() {
                    putStringArrayListExtra(KeyConstants.PUSH_DATA, players as ArrayList<String>)
                }
            } else {
                Toast.makeText(this, "Debe agregar jugadores", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textView13.setOnClickListener {
            val manual = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
            val view = LayoutInflater.from(applicationContext).inflate(R.layout.layout_manual,
                findViewById<ConstraintLayout>(R.id.bottomSheetContainer)
            )
            manual.setContentView(view)
            manual.show()
        }

    }

    private fun testing() {
        val data =
            "[{\"id\": 6, \"title\": \"5 años\", \"isCorrect\": \"0\"}, {\"id\": 7, \"title\": \"200 años\", \"isCorrect\": \"0\"}, {\"id\": 8, \"title\": \"10 años\", \"isCorrect\": \"0\"}, {\"id\": 9, \"title\": \"mas 450 años\", \"isCorrect\": \"1\"}]"
        val gson = Gson()
        val datex: List<Answer> = gson.fromJson(data, Array<Answer>::class.java).toList()

        for (item in datex) {
            Log.d("Bitelito", item.title)
        }


    }

    override fun onClick(pos: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar jugador")
        builder.setMessage(players[pos])

        builder.setPositiveButton("Sí") { dialog, _ ->
            players.removeAt(pos)
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }
}