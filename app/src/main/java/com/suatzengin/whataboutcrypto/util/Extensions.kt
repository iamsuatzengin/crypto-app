package com.suatzengin.whataboutcrypto.util

fun Double.format(digits: Int) = "%.${digits}f".format(this)