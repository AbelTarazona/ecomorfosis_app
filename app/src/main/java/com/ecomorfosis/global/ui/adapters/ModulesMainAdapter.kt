package com.ecomorfosis.global.ui.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ecomorfosis.global.R
import com.ecomorfosis.global.databinding.ItemMainBinding
import com.ecomorfosis.global.models.ItemMain
import com.ecomorfosis.global.utils.getDrawableImg
import com.ecomorfosis.global.utils.inflate

/**
 * Created by AbelTarazona on 29/11/2020
 */
class ModulesMainAdapter(
    private val list: List<ItemMain>,
    private val callback: Callback,
    val context: Context
) : RecyclerView.Adapter<ModulesMainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_main)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            callback.onModuleClick(item.id)
        }
    }

    override fun getItemCount(): Int = list.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemMainBinding.bind(view)

        fun bind(data: ItemMain) {
            with(binding) {
                textView5.text = data.title
                imageView4.setImageDrawable(context.getDrawableImg(data.icon))
                imageView5.setImageDrawable(context.getDrawableImg(data.img))
                itemView.background = context.getDrawableImg(data.background)
            }
        }
    }

    interface Callback {
        fun onModuleClick(id: Int)
    }


}