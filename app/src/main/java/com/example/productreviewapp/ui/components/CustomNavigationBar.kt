package com.example.productreviewapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.productreviewapp.ui.icons.Draw_abstract
import com.example.productreviewapp.ui.screens.AccountScreenRoute
import com.example.productreviewapp.ui.screens.HomeScreenRoute
import com.example.productreviewapp.ui.screens.SelectProductScreenRoute
import com.example.productreviewapp.ui.theme.BlueGradient

@Composable
fun CustomNavigationBar(navController: NavController){
    NavigationBar(
        containerColor = Color.Transparent,
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(Brush.linearGradient(
                colors = listOf(Color.White, BlueGradient[0]),
                start = Offset(0f, 2000f),  // empuja el blanco hacia abajo
                end = Offset(800f, 0f)
            ))
    ) {
        NavigationBarItem(
            selected = false,
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "home",
                    modifier = Modifier.size(35.dp),
                    tint= Color.White
                )
            },
            label = {
                Text("Home", color = Color.White)
            },
            onClick = {
                navController.navigate(HomeScreenRoute){
                    launchSingleTop = true
                    popUpTo(navController.graph.startDestinationId){
                        saveState = true
                    }
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            selected = false,

            icon = {
                Icon(
                    imageVector = Draw_abstract,
                    contentDescription = "Versus",
                    tint = Color.White
                )
            },
            label = {
                Text("Versus", color = Color.White)
            },
            onClick = {
                navController.navigate(SelectProductScreenRoute){
                    launchSingleTop = true
                    popUpTo(navController.graph.startDestinationId){
                        saveState = true
                    }
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            selected = false,

            icon = {
                Icon(
                    imageVector = Icons.Rounded.AccountCircle,
                    contentDescription = "Account",
                    tint = Color.White
                )
            },
            label = {
                Text("Account", color = Color.White)
            },
            onClick = {
                navController.navigate(AccountScreenRoute){
                    launchSingleTop = true
                    popUpTo(navController.graph.startDestinationId){
                        saveState = true
                    }
                    restoreState = true
                }
            }
        )
    }
}