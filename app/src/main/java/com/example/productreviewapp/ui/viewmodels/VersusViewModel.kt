package com.example.productreviewapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productreviewapp.data.RetrofitClient
import com.example.productreviewapp.domain.models.Product
import kotlinx.coroutines.launch

class VersusViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    val firstProductId: String = savedStateHandle["firstProductId"]!!
    val secondProductId: String = savedStateHandle["secondProductId"]!!
    val service = RetrofitClient.createProductService()
    var firstProduct by mutableStateOf<Product?>(null)
    var secondProduct by mutableStateOf<Product?>(null)
    var loading by mutableStateOf(false)

    init {
        loadSelectedProducts()
    }

    fun loadSelectedProducts(){
        viewModelScope.launch {
            loading = true
            try {
                val firstResult = service.getProductById(firstProductId)
                firstProduct = firstResult

                val secondResult = service.getProductById(secondProductId)
                secondProduct = secondResult
            }
            catch (e: Exception){
                Log.e("VersusScreen", e.toString())

            }
            finally {
                loading = false
            }
        }
    }
}