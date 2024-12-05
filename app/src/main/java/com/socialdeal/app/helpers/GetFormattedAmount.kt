package com.socialdeal.app.helpers

fun getFormattedAmount(amount: Int): String {
    return "%.2f".format(amount / 100.0)
}