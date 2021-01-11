package com.ecomorfosis.global.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ecomorfosis.global.R
import com.ecomorfosis.global.databinding.ActivitySessionGameBinding
import com.ecomorfosis.global.models.LeaderBoard
import com.ecomorfosis.global.models.Question
import com.ecomorfosis.global.models.QuestionScreen
import com.ecomorfosis.global.ui.adapters.LeaderboardAdapter
import com.ecomorfosis.global.ui.adapters.SelectPlayerAdapter
import com.ecomorfosis.global.ui.dialog.DialogExitTablero
import com.ecomorfosis.global.utils.DataState
import com.ecomorfosis.global.utils.KeyConstants
import com.ecomorfosis.global.utils.statusBarColorNew
import com.ecomorfosis.global.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SessionGameActivity : AppCompatActivity(),
    SelectPlayerAdapter.Callback,
    DialogExitTablero.Callback {

    private val binding by viewBinding(ActivitySessionGameBinding::inflate)
    private val viewModel: SessionGameViewModel by viewModels()


    private val listLeaderboard: MutableList<LeaderBoard> = mutableListOf()
    private val listPlayers: MutableList<String> = mutableListOf()
    private val listQuestions: MutableList<Question> = mutableListOf()

    private val adapterLeaderBoard = LeaderboardAdapter(listLeaderboard)
    private val adapterPlayers = SelectPlayerAdapter(listPlayers, this)

    private var playerName: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        statusBarColorNew(R.color.background_card_question)

        subscriberObservers()

        viewModel.setStateEvent(MainStateEvent.GetQuestions)

        val intent: Intent = intent
        val data =
            intent.getStringArrayListExtra(KeyConstants.PUSH_DATA)

        if (data != null) {
            listPlayers.addAll(data)
            adapterPlayers.notifyDataSetChanged()
            for (player in listPlayers) {
                listLeaderboard.add(LeaderBoard(player))
            }
            adapterLeaderBoard.notifyDataSetChanged()
        }

        binding.rvJugaddores.adapter = adapterPlayers
        binding.rvLeaderbord.adapter = adapterLeaderBoard

        binding.close.setOnClickListener {
            exitDialog()
        }

        binding.btnStartGame.setOnClickListener {
            if (playerName.isNotEmpty()) {

                val question = listQuestions.random()
                val data = QuestionScreen(
                    name = playerName,
                    question = question
                )

                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra(KeyConstants.PUSH_DATA, data)
                startActivityForResult(intent, KeyConstants.LAUNCH_QUESTION_ACTIVITY)

            } else {
                Toast.makeText(this, "Seleccione un jugador", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == KeyConstants.LAUNCH_QUESTION_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                val playerName: String? = data?.getStringExtra("player")
                val correct: Int? = data?.getIntExtra("correct", 0)


                if (playerName != null) {
                    for (player in listLeaderboard) {
                        if (playerName == player.name) {
                            val new = player.copy(point = player.point + correct!!)
                            listLeaderboard.remove(player)
                            listLeaderboard.add(new)
                            listLeaderboard.sortByDescending {
                                it.point
                            }
                            adapterLeaderBoard.notifyDataSetChanged()
                            break
                        }
                    }
                }

            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
                Toast.makeText(this, "¡Ups! Pregunta no respondida", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClickPlayer(name: String) {
        playerName = name
    }

    override fun onBackPressed() {
        exitDialog()
    }

    private fun exitDialog() {
        val dialog = DialogExitTablero(this)
        dialog.init(this)
        dialog.showDialog()
    }

    override fun onCloseGame() {
        finish()
    }

    private fun subscriberObservers() {

        viewModel.dataState.observe(this, {
            when (it) {
                is DataState.Success<List<Question>> -> {
                    listQuestions.addAll(it.data)
                    displayProgressBar(false)
                    val size = it.data.size
                    binding.tvQuestionNumbers.text =
                        "Se han cargado $size preguntas sobre medio ambiente"
                    binding.btnStartGame.visibility = View.VISIBLE
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(it.exception.message)
                    binding.btnStartGame.visibility = View.GONE
                }
                is DataState.ErrorMessage -> {
                    displayProgressBar(false)
                    displayError(it.msg)
                    binding.btnStartGame.visibility = View.GONE
                }
                DataState.Loading -> displayProgressBar(true)
            }
        })

    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun displayError(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Unknown", Toast.LENGTH_SHORT).show()
        }
    }

}