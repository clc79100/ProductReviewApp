package com.example.productreviewapp.ui.screens.reviewScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productreviewapp.ui.components.CustomLoading
import com.example.productreviewapp.ui.screens.reviewScreen.components.BottomComments
import com.example.productreviewapp.ui.screens.reviewScreen.components.CommentSection
import com.example.productreviewapp.ui.screens.reviewScreen.components.HeaderReview
import com.example.productreviewapp.ui.screens.reviewScreen.components.ReviewContent
import com.example.productreviewapp.ui.theme.background
import com.example.productreviewapp.ui.viewmodels.ReviewViewModel


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
            .background(Color.White)
            .padding(paddingValues)
            .fillMaxSize()
    ){
        HeaderReview(
            vm = vm,
            navController = navController
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .background(background)
                .padding(all = 16.dp)
        ) {
            ReviewContent(
                vm = vm
            )
        }
        BottomComments(
            vm = vm
        )
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