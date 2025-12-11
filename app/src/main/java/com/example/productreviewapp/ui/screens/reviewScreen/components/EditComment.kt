package com.example.productreviewapp.ui.screens.reviewScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.productreviewapp.ui.components.CustomRegularButon

@Composable
fun UpdateComment(
    oldContent: String,
    updateComment: (content:String) -> Unit
){
    var content by remember { mutableStateOf(oldContent) }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = content,
            onValueChange = {content = it},
            label = {Text("Cotenido del Comentario")},
            shape = CircleShape,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            maxLines = 2
        )

        CustomRegularButon("Editar Comentario") {
            updateComment(
                content
            )
        }

    }
}