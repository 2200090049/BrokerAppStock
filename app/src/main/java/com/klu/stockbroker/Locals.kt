package com.example.stockbroker.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StockRepository @Inject constructor(
    private val stockDao: StockDao,
    private val stockApi: StockApi
) {
    fun getPortfolio(userId: Int): Flow<List<Portfolio>> {
        return stockDao.getPortfolio(userId)
    }

    suspend fun insertPortfolio(portfolio: Portfolio) {
        stockDao.insertPortfolio(portfolio)
    }

    suspend fun deletePortfolio(id: Int) {
        stockDao.deletePortfolio(id)
    }

    suspend fun getStockList(): List<Stock> {
        return stockApi.getStockList()
    }

    suspend fun getStockDetails(symbol: String): Stock {
        return stockApi.getStockDetails(symbol)
    }
}
