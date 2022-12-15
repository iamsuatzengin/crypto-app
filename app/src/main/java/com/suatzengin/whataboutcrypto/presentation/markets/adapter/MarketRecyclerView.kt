package com.suatzengin.whataboutcrypto.presentation.markets.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.suatzengin.whataboutcrypto.data.remote.dto.markets.Exchange

class MarketRecyclerView: ListAdapter<Exchange, MarketViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        return MarketViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<Exchange>(){
        override fun areItemsTheSame(oldItem: Exchange, newItem: Exchange): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Exchange, newItem: Exchange): Boolean {
            return oldItem.id == newItem.id
        }

    }


}