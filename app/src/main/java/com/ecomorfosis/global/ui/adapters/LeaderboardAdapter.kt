package com.ecomorfosis.global.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ecomorfosis.global.R
import com.ecomorfosis.global.databinding.ItemLeaderboardBinding
import com.ecomorfosis.global.models.LeaderBoard
import com.ecomorfosis.global.utils.inflate

/**
 * Created by AbelTarazona on 5/12/2020
 */
class LeaderboardAdapter(
    private val items: List<LeaderBoard>
) : RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_leaderboard)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemLeaderboardBinding.bind(view)

        fun bind(lead: LeaderBoard) {
            with(binding) {
                tvPlayer.text = lead.name
                val points = "${lead.point} pts"
                tvPoints.text = points
            }
        }
    }


}