package com.ecomorfosis.global.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.ecomorfosis.global.R
import com.ecomorfosis.global.databinding.ItemContainerAnswerBinding
import com.ecomorfosis.global.models.Answer
import com.ecomorfosis.global.models.Question
import com.ecomorfosis.global.utils.inflate
import kotlinx.android.synthetic.main.item_container_answer.view.*

/**
 * Created by AbelTarazona on 30/08/2020
 */
class AnswersAdapter(private val items: List<Answer>) :
    RecyclerView.Adapter<AnswersAdapter.ViewHolder>() {

    private var lastChecked : CheckBox? = null
    private var answerSelected : Answer? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_container_answer)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position)
        if (lastChecked != null) lastChecked?.isChecked = false
        holder.itemView.cbAnswer.setOnClickListener {
            answerSelected = item
            val checkBox: CheckBox = it as CheckBox
            if (lastChecked != null) {
                lastChecked?.isChecked = false
            }
            lastChecked = checkBox
        }
    }

    override fun getItemCount(): Int = items.size

    fun getAnswerSelected() = answerSelected

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemContainerAnswerBinding.bind(view)

        fun bind(answer: Answer, pos: Int) {
            with(binding) {
                cbAnswer.text = answer.title
            }
        }
    }
}