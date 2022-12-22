package com.suatzengin.whataboutcrypto.util

import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinItem

interface OnClickListener {

    fun onItemClick(coin: CoinItem)
}