package com.example.productreviewapp.ui.screens.reviewScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productreviewapp.ui.components.CustomLoading
import com.example.productreviewapp.ui.screens.reviewScreen.components.CommentSection
import com.example.productreviewapp.ui.viewmodels.ReviewViewModel
import dev.jeziellago.compose.markdowntext.MarkdownText


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ReviewScreen(
    paddingValues: PaddingValues,
    navController: NavController){
    val vm: ReviewViewModel = viewModel()


    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(vm.review?.title ?: "")
            MarkdownText(vm.review?.content ?: "")
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {vm.showSheet = true}
            ) {
                Text("Comentarios")
            }
            Button(
                onClick = { navController.popBackStack() }
            ) {
                Text("Boton atras")
            }
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