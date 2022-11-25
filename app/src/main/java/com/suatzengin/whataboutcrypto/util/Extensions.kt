package com.suatzengin.whataboutcrypto.util

fun Double.format(digits: Int) = "%.${digits}f".format(this)

fun String.addPrefix(value: String) = this + value

fun String.addSuffix(value: String) = value + this