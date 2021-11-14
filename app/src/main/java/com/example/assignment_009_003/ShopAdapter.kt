package com.example.assignment_009_003

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_009_003.databinding.ShopItemBinding
import com.example.assignment_009_003.extensions.glideExtension
import com.example.assignment_009_003.model.ShopItem


class ShopAdapter : RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {

    private val list: MutableList<ShopItem> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ShopItem>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        return ShopViewHolder(
            ShopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        )
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ShopViewHolder(private val binding: ShopItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var shopModel: ShopItem

        fun onBind() {
            shopModel = list[adapterPosition]
            binding.price.text = shopModel.price
            binding.title.text = shopModel.title
            binding.image.glideExtension(shopModel.cover)
        }


    }
}