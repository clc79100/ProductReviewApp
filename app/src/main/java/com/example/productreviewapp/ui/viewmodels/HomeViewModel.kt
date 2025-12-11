package com.example.productreviewapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productreviewapp.data.RetrofitClient
import com.example.productreviewapp.domain.models.Review
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel(){
    val service = RetrofitClient.createReviewService()
    var mainReviews by mutableStateOf<List<Review>>(emptyList())
    var reviewsByCategory by mutableStateOf<List<Review>>(emptyList()) //por la categoria del producto
    var reviewsByReviewer by mutableStateOf<List<Review>>(emptyList())
    var loading by mutableStateOf(false)

    init {
        loadReviews()
    }

    fun loadReviews(){
        viewModelScope.launch {
            loading = true
            try {
                val result = service.getAllReviews()
                mainReviews = result

                val resultByCategory = service.getAllReviewsByCategory("IEM")
                reviewsByCategory = resultByCategory

                val resultByReviewer = service.getAllReviewsByReviewer("Suprapixel")
                reviewsByReviewer = resultByReviewer
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