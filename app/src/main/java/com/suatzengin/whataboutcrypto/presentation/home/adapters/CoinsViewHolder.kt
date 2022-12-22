package com.suatzengin.whataboutcrypto.presentation.home.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.suatzengin.whataboutcrypto.R
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinItem
import com.suatzengin.whataboutcrypto.databinding.CoinListItemBinding
import com.suatzengin.whataboutcrypto.util.OnClickListener
import com.suatzengin.whataboutcrypto.util.addPrefix
import com.suatzengin.whataboutcrypto.util.addSuffix
import java.util.*

class CoinsViewHolder(
    private val binding: CoinListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CoinItem, onClickListener: OnClickListener) {

        binding.apply {
            itemLayoutId.setOnClickListener{
                onClickListener.onItemClick(item)
            }

            coinImage.load(item.image) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }

            tvCoinName.text = item.name
            tvCoinSymbol.text = item.symbol.uppercase(Locale.ROOT)
            tvCoinPrice.text = item.currentPrice.toString().addPrefix("$") //custom string extensions
            tvPercentage.text = item.priceChangePercentage24h.toString().addSuffix("%")
        }
        if (item.priceChangePercentage24h > 0) {
            binding.tvPercentage.setTextColor(
                Color.parseColor("#50C878")
            )
            binding.itemLayoutId.setBackgroundResource(R.drawable.coins_item_bg_increase)
        } else {
            binding.tvPercentage.setTextColor(
                Color.parseColor("#FF5733")
            )
            binding.itemLayoutId.setBackgroundResource(R.drawable.coins_item_bg_decrease)
        }
    }


    companion object {
        fun from(parent: ViewGroup): CoinsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CoinListItemBinding.inflate(layoutInflater, parent, false)
            return CoinsViewHolder(binding)
        }
    }
}