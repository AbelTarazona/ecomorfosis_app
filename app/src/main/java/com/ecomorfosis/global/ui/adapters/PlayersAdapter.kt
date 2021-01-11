package com.ecomorfosis.global.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ecomorfosis.global.R
import com.ecomorfosis.global.databinding.ItemContainerPlayerBinding
import com.ecomorfosis.global.mock.getIcons
import com.ecomorfosis.global.utils.inflate

/**
 * Created by AbelTarazona on 27/08/2020
 */
class PlayersAdapter(private val items: List<String>, private val callback: Callback) : RecyclerView.Adapter<PlayersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_container_player)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position, callback)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val icons = getIcons()

        private val binding = ItemContainerPlayerBinding.bind(view)

        fun bind(playerName : String, pos: Int, callback: Callback) {
            with(binding) {
                ivIcon.setImageResource(icons.random())
                tvPlayer.text = playerName
                root.setOnClickListener {
                    callback.onClick(pos)
                }
            }
        }
    }

    interface Callback {
        fun onClick(pos: Int)
    }
}

