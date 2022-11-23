package com.suatzengin.whataboutcrypto.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.suatzengin.whataboutcrypto.data.remote.dto.CoinItem

class HomeRecyclerAdapter: ListAdapter<CoinItem, HomeViewHolder>(DiffCallBack){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    companion object DiffCallBack: DiffUtil.ItemCallback<CoinItem>(){
        override fun areItemsTheSame(oldItem: CoinItem, newItem: CoinItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CoinItem, newItem: CoinItem): Boolean {
            return oldItem.id == newItem.id
        }

    }
}