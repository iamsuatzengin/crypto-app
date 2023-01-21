package com.suatzengin.whataboutcrypto.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.suatzengin.whataboutcrypto.data.remote.dto.search.CoinSearchResponse
import com.suatzengin.whataboutcrypto.databinding.SearchItemBinding


class SearchViewHolder(private val binding: SearchItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CoinSearchResponse) {
        with(binding) {
            tvMarketRank.text = item.marketCapRank.toString()
            ivIcon.load(item.imageUrl) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            tvName.text = item.name
        }
    }

    companion object {
        fun from(parent: ViewGroup): SearchViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SearchItemBinding.inflate(layoutInflater, parent, false)
            return SearchViewHolder(binding)
        }
    }
}