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
    @Composable 
fun AddTradeScreen( 
onSaveClick: (Trade) -> Unit, 
onCancelClick: () -> Unit 
) { 
var stockSymbol by remember { mutableStateOf("") } 
var quantity by remember { mutableStateOf("") } 
var selectedOrderType by remember { mutableStateOf("Market") } 
var selectedAction by remember { mutableStateOf("Buy") } 
val orderTypeOptions = listOf("Market", "Limit") 
val actionOptions = listOf("Buy", "Sell") 
Scaffold( 
topBar = { 
TopAppBar( 
title = { Text("New Trade") }, 
navigationIcon = { 
IconButton(onClick = { onCancelClick() }) { 
Icon(Icons.Default.ArrowBack, contentDescription = "Back") 
} 
12  
13  
  
                } 
            ) 
        }, 
        floatingActionButton = { 
            FloatingActionButton(onClick = { 
                val trade = Trade( 
                    stockSymbol = stockSymbol, 
                    quantity = quantity.toIntOrNull() ?: 0, 
                    orderType = selectedOrderType, 
                    action = selectedAction 
                ) 
                onSaveClick(trade) 
            }) { 
                Icon(Icons.Default.Check, contentDescription = "Save Trade") 
            } 
        } 
    ) { padding -> 
        Column( 
            modifier = Modifier 
                .padding(padding) 
                .padding(16.dp) 
                .fillMaxSize(), 
            verticalArrangement = Arrangement.spacedBy(16.dp) 
        ) { 
            OutlinedTextField( 
                value = stockSymbol, 
                onValueChange = { stockSymbol = it.uppercase() }, 
                label = { Text("Stock Symbol") }, 
                modifier = Modifier.fillMaxWidth() 
            ) 
 
            OutlinedTextField( 
                value = quantity, 
                onValueChange = { quantity = it }, 
                label = { Text("Quantity") }, 
                modifier = Modifier.fillMaxWidth(), 
                keyboardOptions = KeyboardOptions(keyboardType = 
KeyboardType.Number) 
            ) 
 
            DropdownSelector( 
                label = "Order Type", 
                options = orderTypeOptions, 
                selectedOption = selectedOrderType, 
                onOptionSelected = { selectedOrderType = it } 
            ) 
 
            DropdownSelector( 
14  
  
                label = "Action", 
                options = actionOptions, 
                selectedOption = selectedAction, 
                onOptionSelected = { selectedAction = it } 
            ) 
        } 
    } 
} 
 
@Composable 
fun DropdownSelector( 
    label: String, 
    options: List<String>, 
    selectedOption: String, 
    onOptionSelected: (String) -> Unit 
) { 
    var expanded by remember { mutableStateOf(false) } 
 
    Column { 
        Text(text = label, style = MaterialTheme.typography.labelMedium) 
        Box { 
            OutlinedButton( 
                onClick = { expanded = true }, 
                modifier = Modifier.fillMaxWidth() 
            ) { 
                Text(selectedOption) 
            } 
            DropdownMenu( 
                expanded = expanded, 
                onDismissRequest = { expanded = false } 
            ) { 
                options.forEach { option -> 
                    DropdownMenuItem( 
                        text = { Text(option) }, 
                        onClick = { 
                            onOptionSelected(option) 
                            expanded = false 
                        } 
                    ) 
                } 
            } 
        } 
    } 
} 
 
// Sample Data Class 
data class Trade( 
    val stockSymbol: String, 
)  
val quantity: Int, 
val orderType: String, 
val action: String

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
