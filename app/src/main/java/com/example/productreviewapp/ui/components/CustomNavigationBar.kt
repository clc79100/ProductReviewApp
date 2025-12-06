package com.example.productreviewapp.ui.components

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.productreviewapp.ui.icons.Draw_abstract
import com.example.productreviewapp.ui.screens.AccountScreenRoute
import com.example.productreviewapp.ui.screens.HomeScreenRoute
import com.example.productreviewapp.ui.screens.VersusScreenRoute

@Composable
fun CustomNavigationBar(navController: NavController){
    NavigationBar(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
    ) {
        NavigationBarItem(
            selected = false,

            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "home",
                    modifier = Modifier.size(35.dp)
                )
            },
            label = {
                Text("Home")
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
                    contentDescription = "Versus"
                )
            },
            label = {
                Text("Versus")
            },
            onClick = {
                navController.navigate(VersusScreenRoute){
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
                )
            },
            label = {
                Text("Account")
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