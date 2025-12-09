package com.example.productreviewapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productreviewapp.data.RetrofitClient
import com.example.productreviewapp.domain.models.Product
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {
    val service = RetrofitClient.createProductService()
    var products by mutableStateOf<List<Product>>(emptyList())
    var firstProduct by mutableStateOf<Product?>(null)
    var secondProduct by mutableStateOf<Product?>(null)
    var isFirstButtonClicked by mutableStateOf(false)
    var isSecondButtonClicked by mutableStateOf(false)
    var isEnabled by mutableStateOf(false)
    var showSheet by mutableStateOf(false)
    var loading by mutableStateOf(false)

    fun loadProducts(){
        viewModelScope.launch {
            loading = true
            try {
                val result = async {
                    service.getAllProducts()
                }
                products = result.await()
                delay(1000)
            }
            catch (e: Exception){
                Log.e("HomeScreen", e.toString())
            }
            finally {
                loading = false
            }
        }
    }
}