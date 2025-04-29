package com.example.stockbroker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portfolio")
data class Portfolio(
    @PrimaryKey val id: Int,
    val userId: Int,
    val symbol: String,
    val quantity: Int,
    val purchasePrice: Double,
    val purchaseDate: String
)