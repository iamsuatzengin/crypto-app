package com.suatzengin.whataboutcrypto.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.suatzengin.whataboutcrypto.data.remote.dto.TrendingCoin


class TrendingCoinsAdapter :
    ListAdapter<TrendingCoin, TrendingViewHolder>(DiffCallBackTrending) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallBackTrending : DiffUtil.ItemCallback<TrendingCoin>() {
        override fun areItemsTheSame(
            oldItem: TrendingCoin,
            newItem: TrendingCoin
        ): Boolean {
            return oldItem.item == newItem.item
        }

        override fun areContentsTheSame(
            oldItem: TrendingCoin,
            newItem: TrendingCoin
        ): Boolean {
            return oldItem.item.id == newItem.item.id
        }
    }
}
