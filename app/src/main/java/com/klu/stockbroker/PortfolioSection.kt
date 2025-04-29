package com.example.stockbroker.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stockbroker.data.Portfolio

@Composable
fun PortfolioScreen(viewModel: PortfolioViewModel = hiltViewModel()) {
    val portfolio = viewModel.getPortfolio(1).collectAsState(initial = emptyList()).value
    LazyColumn {
        items(portfolio) { item ->
            PortfolioItem(item, onSell = { viewModel.sellStock(item.id) })
        }
    }
}

@Composable
fun PortfolioItem(portfolio: Portfolio, onSell: () -> Unit) {
    Text(
        text = "${portfolio.symbol}: ${portfolio.quantity} shares",
        modifier = androidx.compose.ui.Modifier.padding(16.dp)
    )
    Button(onClick = onSell) {
        Text("Sell")
    }
}
