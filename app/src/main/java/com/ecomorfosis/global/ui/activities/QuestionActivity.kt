package com.ecomorfosis.global.ui.activities

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ecomorfosis.global.R
import com.ecomorfosis.global.databinding.ActivityQuestionBinding
import com.ecomorfosis.global.models.Answer
import com.ecomorfosis.global.models.Question
import com.ecomorfosis.global.models.QuestionScreen
import com.ecomorfosis.global.ui.adapters.AnswersAdapter
import com.ecomorfosis.global.ui.dialog.DialogErrorTablero
import com.ecomorfosis.global.ui.dialog.DialogSucessTablero
import com.ecomorfosis.global.utils.KeyConstants
import com.ecomorfosis.global.utils.statusBarColorNew
import com.ecomorfosis.global.utils.viewBinding


class QuestionActivity : AppCompatActivity(), DialogErrorTablero.Callback,
    DialogSucessTablero.Callback {

    private val binding by viewBinding(ActivityQuestionBinding::inflate)

    private var list = mutableListOf<Answer>()
    private var adapter: AnswersAdapter = AnswersAdapter(list)

    private lateinit var playerSuccess: MediaPlayer
    private lateinit var playerError: MediaPlayer

    private lateinit var question: Question
    private lateinit var questionScreen: QuestionScreen


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        statusBarColorNew(R.color.background_card_question)

        playerSuccess = MediaPlayer.create(this, R.raw.success)
        playerError = MediaPlayer.create(this, R.raw.error)

        val intent: Intent = intent
        val data =
            intent.getParcelableExtra<QuestionScreen>(KeyConstants.PUSH_DATA)

        binding.rvAnswers.adapter = adapter

        if (data != null) {
            question = data.question
            questionScreen = data
            val answers = data.question.getListAnswers()
            list.addAll(answers)
            adapter.notifyDataSetChanged()
            binding.textView11.text = "Jugando ${data.name}"
            binding.tvTitleQuestion.text = question.title
        }

        binding.btnValidateQuestion.setOnClickListener {

            val answerSelected = adapter.getAnswerSelected()

            if (answerSelected != null) {

                if (answerSelected.isCorrect == "1") {
                    playerSuccess.start()

                    val dialog = DialogSucessTablero(question, this)
                    dialog.init(this)
                    dialog.showDialog()

                } else {
                    playerError.start()

                    val dialog = DialogErrorTablero(question, this)
                    dialog.init(this)
                    dialog.showDialog()
                }
            } else {
                Toast.makeText(it.context, "Escoja una opcion", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateNewQuestion(binding: ActivityQuestionBinding) {
/*        val question: Question = listQuestions.random()
        binding.tvTitleQuestion.text = question.title

        val listAnswer: List<Answer> = question.answers
        answers.clear()
        answers.addAll(listAnswer)
        adapter.notifyDataSetChanged()*/
    }

    override fun incorrectAnswer() {
        val returnIntent = Intent()
        returnIntent.putExtra("player", questionScreen.name)
        returnIntent.putExtra("correct", -1)
        setResult(RESULT_OK, returnIntent)
        finish()
    }

    override fun correctAnswer() {
        val returnIntent = Intent()
        returnIntent.putExtra("player", questionScreen.name)
        returnIntent.putExtra("correct", 1)
        setResult(RESULT_OK, returnIntent)
        finish()
    }

}