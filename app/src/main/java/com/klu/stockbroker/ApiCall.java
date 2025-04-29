package com.example.stockbroker.data

data class Stock(
        val id: Int,
        val symbol: String,
        val name: String,
        val price: Double,
        val change: Double,
        val volume: Long
)
