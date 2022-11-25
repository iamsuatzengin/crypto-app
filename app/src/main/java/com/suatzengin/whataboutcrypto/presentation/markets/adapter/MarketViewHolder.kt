package com.suatzengin.whataboutcrypto.presentation.markets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.suatzengin.whataboutcrypto.data.remote.dto.markets.Exchange
import com.suatzengin.whataboutcrypto.databinding.MarketListItemBinding

class MarketViewHolder(
    private val binding: MarketListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Exchange) {
        binding.apply {
            ivMarketIcon.load(item.image){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            tvScoreRank.text = item.trustScoreRank.toString()
            tvMarketName.text = item.name
            tvYearEstablished.text = (item.yearEstablished ?: "Unknown").toString()
            tvTrustScore.text = item.trustScore.toString()
            tvCountry.text = item.country ?: "Unknown"
        }
    }

    companion object {
        fun from(parent: ViewGroup): MarketViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = MarketListItemBinding.inflate(layoutInflater, parent, false)
            return MarketViewHolder(binding)
        }
    }
}