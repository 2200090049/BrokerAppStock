package com.example.stockbroker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockbroker.data.Stock
import com.example.stockbroker.data.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val repository: StockRepository
) : ViewModel() {
    private val _stocks = MutableStateFlow<List<Stock>>(emptyList())
    val stocks: StateFlow<List<Stock>> = _stocks

    init {
        fetchStocks()
    }

    private fun fetchStocks() {
        viewModelScope.launch {
            try {
                _stocks.value = repository.getStockList()
            } catch (e: Exception) {
                _stocks.value = emptyList()
            }
        }
    }

    fun getStockDetails(symbol: String) {
        viewModelScope.launch {
            try {
                val stock = repository.getStockDetails(symbol)
                _stocks.value = listOf(stock)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
