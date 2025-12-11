package com.example.productreviewapp.ui.screens.reviewScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.productreviewapp.domain.models.User
import com.example.productreviewapp.ui.viewmodels.ReviewViewModel

@Composable
fun CommentSection (vm: ReviewViewModel){

    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        CommentOption(
            vm = vm
        )
        if(vm.review?.comments.isNullOrEmpty()){
            Text("No hay comentarios")
        } else{
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(items = vm.review!!.comments.sortedByDescending { it.createdAt }){ comment ->
                    val user: User = comment.user ?: User(id = "no_id", "[deleted_user]", null)
                    Comment(
                        vm = vm,
                        user = user,
                        comment = comment
                    )
                }
            }
        }
    }
}