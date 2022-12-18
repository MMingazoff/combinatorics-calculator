package com.itis.combinatoricscalculator

fun Int.factorial(): Long {
    var result = 1L
    for (i in 1..this)
        result *= i
    return result
}

fun Int.power(power: Int): Long {
    var result = 1L
    for (i in 1..power)
        result *= this
    return result
}