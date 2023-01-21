package com.suatzengin.whataboutcrypto.presentation.search.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.suatzengin.whataboutcrypto.data.remote.dto.search.CoinSearchResponse

class SearchRecyclerAdapter :
    ListAdapter<CoinSearchResponse, SearchViewHolder>(SearchDiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object SearchDiffCallBack : DiffUtil.ItemCallback<CoinSearchResponse>() {
        override fun areItemsTheSame(
            oldItem: CoinSearchResponse,
            newItem: CoinSearchResponse
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: CoinSearchResponse,
            newItem: CoinSearchResponse
        ): Boolean = oldItem.id == newItem.id
    }
}
