package com.example.iptesttask.roomdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iptesttask.main_screen.ListItem
import com.example.iptesttask.roomdb.data.MainDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainDb: MainDb
): ViewModel() {
    val flowList = mainDb.dao.getAllItemsFlow()

    fun updateItem(item: ListItem) = viewModelScope.launch {
         mainDb.dao.updateItem(item)
    }

    fun deleteItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.deleteItem(item)
    }

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    val products = searchText
        .combine(flowList) { text, products ->
            if (text.isBlank()) {
                products
            } else {
                products.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun  onSearchTextChange(text:String) {
        _searchText.value = text
    }
}
