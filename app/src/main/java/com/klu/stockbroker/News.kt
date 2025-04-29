package com.example.stockbroker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockbroker.data.Portfolio
import com.example.stockbroker.data.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val repository: StockRepository
) : ViewModel() {
    fun getPortfolio(userId: Int): StateFlow<List<Portfolio>> {
        return repository.getPortfolio(userId)
    }

    fun buyStock(portfolio: Portfolio) {
        viewModelScope.launch {
            repository.insertPortfolio(portfolio)
        }
    }

    fun sellStock(id: Int) {
        viewModelScope.launch {
            repository.deletePortfolio(id)
        }
    }
}