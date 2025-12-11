package com.example.productreviewapp.ui.screens.reviewScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.productreviewapp.domain.dtos.CommentDTO
import com.example.productreviewapp.domain.models.User
import com.example.productreviewapp.domain.utils.SharedPref
import com.example.productreviewapp.ui.theme.MainPurple
import com.example.productreviewapp.ui.viewmodels.ReviewViewModel

@Composable
fun CommentSection (vm: ReviewViewModel){

    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .clickable {
                    vm.dialogContentComposable = {
                        CreateComment(
                            createComment = { comment ->
                                vm.createComment(comment)
                            }
                        )
                    }
                    vm.showDialog = true
                }
                .background(Color.White)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ChatBubbleOutline,
                contentDescription = "Comentar",
                tint = MainPurple,
                modifier = Modifier
                    .padding(end = 8.dp)
            )
            Text(
                text = "Nuevo Comentario...",
                color = Color.Gray
            )
        }
        if(vm.review?.comments.isNullOrEmpty()){
            Text("No hay comentarios")
        } else{
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(items = vm.review!!.comments.sortedByDescending { it.createdAt }){ comment ->
                    val user: User = comment.user ?: User(id = "no_id", "[deleted_user]", null)
                    Column (
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth()
                            .shadow(elevation = 2.dp, shape = RoundedCornerShape(24.dp))
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color.White)
                            .padding(all = 16.dp)
                    ){
                        //Text(comment.content)
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier
                                    .weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .border(
                                            color = MainPurple,
                                            width = 2.dp,
                                            shape = CircleShape
                                        )
                                        .padding(4.dp),
                                    contentAlignment = Alignment.BottomEnd
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AccountCircle,
                                        contentDescription = null,
                                        tint = Color.Gray,
                                        modifier = Modifier.fillMaxSize()
                                    )

                                    if (!user.profilePhoto.isNullOrEmpty())
                                        AsyncImage(
                                            model = user.profilePhoto,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(CircleShape),
                                            contentScale = ContentScale.Crop
                                        )
                                }
                                Text(
                                    text = user.name,
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                )
                                Text(
                                    text = " · " + "${comment.createdAt.day}/${(comment.createdAt.month+1)}/${comment.createdAt.year-100}",
                                    color = Color.Gray
                                )
                            }

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
                                        imageVector = Icons.Outlined.Edit,
                                        contentDescription = null,
                                        tint = MainPurple
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
                                        imageVector = Icons.Outlined.Delete,
                                        contentDescription = null,
                                        tint = MainPurple
                                    )
                                }
                            }
                        }
                        //Text(text = comment.createdAt.month.toString())
                        Text(comment.content)

                    }
                }
            }
        }
    }
}