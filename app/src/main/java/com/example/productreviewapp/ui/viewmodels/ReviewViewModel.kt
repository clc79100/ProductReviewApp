package com.example.productreviewapp.ui.viewmodels

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productreviewapp.data.RetrofitClient
import com.example.productreviewapp.domain.dtos.CommentDTO
import com.example.productreviewapp.domain.models.Review
import com.example.productreviewapp.domain.utils.SharedPref
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ReviewViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    val reviewService = RetrofitClient.createReviewService()
    val commentService = RetrofitClient.createCommentService()
    val reviewId: String = savedStateHandle["reviewId"]!!
    val auth: String = "Bearer ${SharedPref.getToken()}"
    var review by mutableStateOf<Review?>(null)
    var loading by mutableStateOf(false)
    var showSheet by mutableStateOf(false)
    var showDialog by mutableStateOf(false)
    var dialogContentComposable by mutableStateOf<@Composable () -> Unit>({ Text("No hay nada") })

    init {
        loadReview()
    }

    fun loadReview(){
        viewModelScope.launch {
            loading = true
            try {
                val result = async {
                    reviewService.getReviewById(reviewId)
                }
                review = result.await()
            }
            catch (e: Exception){
                Log.e("HomeScreen", e.toString())
            }
            finally {
                loading = false
            }
        }
    }

    fun createComment(content: String){
        viewModelScope.launch {
            try {
                val newComment = CommentDTO(
                    reviewId = reviewId,
                    content = content
                )
                val result = commentService.createComment(
                    auth = auth,
                    commentDTO = newComment
                )
                Log.i("status code", result.code().toString())
                showDialog = false
                loadReview()
            }
            catch (e: Exception){
                Log.e("Error creating comment",e.toString()) //TODO: no agarra ningun error
            }
        }
    }

    fun updateComment(commentId: String,commentDTO: CommentDTO){
        viewModelScope.launch {
            try {
                commentService.updateComment(
                    auth = auth,
                    id = commentId,
                    commentDTO = commentDTO
                )
                showDialog = false
                loadReview()
            }
            catch (e: Exception){
                Log.e("Error updating comment", e.toString())
            }
        }
    }
    fun deleteComment(commentId: String){
        viewModelScope.launch {
            try {
                commentService.deleteComment(
                    auth = auth,
                    id = commentId
                )
                loadReview()
            }
            catch(e: Exception){
                Log.e("Error deleting comment", e.toString())
            }
        }
    }
}

