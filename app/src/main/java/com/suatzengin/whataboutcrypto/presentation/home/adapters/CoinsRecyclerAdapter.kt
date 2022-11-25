package com.suatzengin.whataboutcrypto.presentation.home.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinItem

class CoinsRecyclerAdapter : ListAdapter<CoinItem, CoinsViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        return CoinsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<CoinItem>() {
        override fun areItemsTheSame(oldItem: CoinItem, newItem: CoinItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CoinItem, newItem: CoinItem): Boolean {
            return oldItem.id == newItem.id
        }

    }
}