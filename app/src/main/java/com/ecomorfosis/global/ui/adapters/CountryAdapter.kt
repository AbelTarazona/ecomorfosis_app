package com.ecomorfosis.global.ui.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ecomorfosis.global.R
import com.ecomorfosis.global.databinding.ItemCountryBinding
import com.ecomorfosis.global.models.Country
import com.ecomorfosis.global.utils.getDrawableImg
import com.ecomorfosis.global.utils.inflate

/**
 * Created by AbelTarazona on 29/11/2020
 */
class CountryAdapter(
    private val list: List<Country>,
    private val callback: Callback,
    val context: Context
) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    var selectedItemPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_country)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        if (position == selectedItemPos) {
            holder.indicatorSelected()
        } else {
            holder.indicatorUnselected()
        }

        holder.bind(item)
        holder.itemView.setOnClickListener {
            selectedItemPos = position
            notifyDataSetChanged()
            callback.onCountrySelected(item)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCountryBinding.bind(view)

        fun bind(data: Country) {
            binding.imageView.setImageDrawable(context.getDrawableImg(data.resource))
        }

        fun indicatorSelected() {
            binding.imageView3.visibility = View.VISIBLE
        }

        fun indicatorUnselected() {
            binding.imageView3.visibility = View.GONE
        }

    }

    interface Callback {
        fun onCountrySelected(data: Country)
    }
}