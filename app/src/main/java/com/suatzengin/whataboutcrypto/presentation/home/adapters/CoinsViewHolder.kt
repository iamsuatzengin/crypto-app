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
import com.suatzengin.whataboutcrypto.util.addPrefix
import com.suatzengin.whataboutcrypto.util.addSuffix
import java.util.Locale

class CoinsViewHolder(
    private val binding: CoinListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CoinItem, onItemClick: (CoinItem) -> Unit) {

        binding.apply {
            itemLayoutId.setOnClickListener{
                onItemClick(item)
            }

            coinImage.load(item.image) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }

            tvCoinName.text = item.name

            tvCoinSymbol.text = item.symbol.uppercase(Locale.ROOT)

            tvCoinPrice.text = item.currentPrice.toString().addPrefix("$") //custom string extensions

            tvPercentage.text = item.priceChangePercentage24h.toString().addSuffix("%")

            tvPercentage.setTextColor(item.color)

            itemLayoutId.setBackgroundResource(item.background)
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

val CoinItem.color: Int
    get() = if(priceChangePercentage24h > 0) {
        Color.parseColor("#50C878")
    } else {
        Color.parseColor("#FF5733")
    }

val CoinItem.background: Int
    get() = if(priceChangePercentage24h > 0) {
        R.drawable.coins_item_bg_increase
    } else {
        R.drawable.coins_item_bg_decrease
    }
