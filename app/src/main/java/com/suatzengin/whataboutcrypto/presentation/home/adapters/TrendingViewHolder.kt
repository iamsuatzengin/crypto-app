package com.suatzengin.whataboutcrypto.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.TrendingCoin
import com.suatzengin.whataboutcrypto.databinding.TrendingCoinsItemBinding
import com.suatzengin.whataboutcrypto.util.format


class TrendingViewHolder(
    private val binding: TrendingCoinsItemBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(coin: TrendingCoin) {
        binding.apply {
            ivTrendIcon.load(coin.item.small) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }

            tvTrendName.text = coin.item.name
            tvCoinRank.text = coin.item.marketCapRank.toString()
            tvTrendPriceBtc.text = coin.item.priceBtc.format(15)
        }
    }


    companion object {
        fun from(parent: ViewGroup): TrendingViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = TrendingCoinsItemBinding.inflate(layoutInflater, parent, false)
            return TrendingViewHolder(binding)
        }
    }
}
