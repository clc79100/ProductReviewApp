package com.example.productreviewapp.ui.screens.reviewScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.productreviewapp.domain.dtos.CommentDTO
import com.example.productreviewapp.domain.models.User
import com.example.productreviewapp.domain.utils.SharedPref
import com.example.productreviewapp.ui.viewmodels.ReviewViewModel

@Composable
fun CommentSection (vm: ReviewViewModel){

    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                vm.dialogContentComposable = {
                    CreateComment(
                        createComment = { comment ->
                            vm.createComment(comment)
                        }
                    )
                }
                vm.showDialog = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Nuevo Comentario")
        }
        if(vm.review?.comments.isNullOrEmpty()){
            Text("No hay comentarios")
        } else{
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(items = vm.review!!.comments.sortedByDescending { it.createdAt }){ comment ->
                    val user: User = comment.user ?: User(id = "no_id", "[deleted_user]", null)
                    Row (
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(24.dp))
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color(0xFFFFFFFF))
                            .padding(8.dp)
                    ){
                        Column(Modifier.weight(1f)){
                            Text(comment.content)
                            Text(user.name)
                        }

                        Text(text = comment.createdAt.month.toString())

                        if (user.id == SharedPref.getUserId()){
                            IconButton(
                                onClick = {
                                    vm.dialogContentComposable = {
                                        UpdateComment(
                                            oldContent = comment.content,
                                            updateComment = { content ->
                                                vm.updateComment(
                                                    commentId = comment.id,
                                                    commentDTO = CommentDTO(
                                                        content = content
                                                    )
                                                )
                                            }
                                        )
                                    }
                                    vm.showDialog = true
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null
                                )
                            }
                            IconButton(
                                onClick = {
                                    vm.deleteComment(
                                        commentId = comment.id
                                    )
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}