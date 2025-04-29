package com.example.stockbroker.data

import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {
    @GET("stocks")
    suspend fun getStockList(): List<Stock>

    @GET("stock/details")
    suspend fun getStockDetails(@Query("symbol") symbol: String): Stock
}