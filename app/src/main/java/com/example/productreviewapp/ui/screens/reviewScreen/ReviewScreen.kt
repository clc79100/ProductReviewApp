package com.example.productreviewapp.ui.screens.reviewScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productreviewapp.ui.components.CustomLoading
import com.example.productreviewapp.ui.screens.reviewScreen.components.CommentSection
import com.example.productreviewapp.ui.theme.background
import com.example.productreviewapp.ui.theme.commentBackground
import com.example.productreviewapp.ui.viewmodels.ReviewViewModel
import dev.jeziellago.compose.markdowntext.MarkdownText


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ReviewScreen(
    paddingValues: PaddingValues,
    navController: NavController
) {
    val vm: ReviewViewModel = viewModel()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    Column (
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás",
                    tint = Color.DarkGray
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = vm.review?.title ?: "Sin título",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(all = 16.dp)
        ) {
            MarkdownText(
                vm.review?.content ?: "Sin contenido",
                modifier = Modifier
                    .padding(bottom = 50.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{
                    vm.showSheet = true
                }
                .padding(all = 16.dp)
        ) {
            val recentComment =
                vm.review?.comments?.maxByOrNull { it.createdAt }

            Text(
                text = "Comentarios",
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = recentComment?.content ?:" No Hay comentarios",
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = commentBackground,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp)
            )

        }
    }

    if(vm.showSheet){
        ModalBottomSheet(
            onDismissRequest = { vm.showSheet = false },
            dragHandle = { BottomSheetDefaults.DragHandle() },
            sheetState = sheetState
        ) {
            CommentSection(vm)
        }
    }

    if(vm.showDialog){
        Dialog(onDismissRequest = { vm.showDialog = false}) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp
            ) {
                vm.dialogContentComposable()
            }
        }
    }

    if (vm.loading){
        CustomLoading()
    }
}