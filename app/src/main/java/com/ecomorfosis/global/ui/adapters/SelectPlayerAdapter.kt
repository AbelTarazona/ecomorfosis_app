package com.ecomorfosis.global.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ecomorfosis.global.R
import com.ecomorfosis.global.databinding.ItemPlayerBinding
import com.ecomorfosis.global.utils.inflate

/**
 * Created by AbelTarazona on 5/12/2020
 */
class SelectPlayerAdapter(
    private val list: List<String>,
    private val callback: Callback
) : RecyclerView.Adapter<SelectPlayerAdapter.ViewHolder>() {

    var selectedItemPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_player)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        if (position == selectedItemPos) {
            holder.indicatorSelected()
        } else {
            holder.indicatorUnselected()
        }

        holder.bind(item, position)

        holder.itemView.setOnClickListener {
            selectedItemPos = position
            notifyDataSetChanged()
            callback.onClickPlayer(item)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemPlayerBinding.bind(view)

        fun bind(playerName: String, pos: Int) {
            with(binding) {
                tvPlayer.text = playerName
            }
        }

        fun indicatorSelected() {
            binding.selector.visibility = View.VISIBLE
        }

        fun indicatorUnselected() {
            binding.selector.visibility = View.GONE
        }
    }

    interface Callback {
        fun onClickPlayer(name: String)
    }

}