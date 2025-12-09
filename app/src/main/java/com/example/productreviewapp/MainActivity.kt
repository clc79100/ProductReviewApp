package com.example.productreviewapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.productreviewapp.ui.components.CustomNavigationBar
import com.example.productreviewapp.ui.screens.accountScreen.AccountScreen
import com.example.productreviewapp.ui.screens.AccountScreenRoute
import com.example.productreviewapp.ui.screens.EditAccountScreenRoute
import com.example.productreviewapp.ui.screens.homeScreen.HomeScreen
import com.example.productreviewapp.ui.screens.HomeScreenRoute
import com.example.productreviewapp.ui.screens.ReviewScreenRoute
import com.example.productreviewapp.ui.screens.VersusScreen
import com.example.productreviewapp.ui.screens.VersusScreenRoute
import com.example.productreviewapp.ui.screens.accountScreen.EditAccountScreen
import com.example.productreviewapp.ui.screens.reviewScreen.ReviewScreen
import com.example.productreviewapp.ui.theme.ProductReviewAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductReviewAppTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination?.route
                val bottomBarDestinations = listOf(
                    HomeScreenRoute::class.qualifiedName,
                    VersusScreenRoute::class.qualifiedName,
                    AccountScreenRoute::class.qualifiedName
                )
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentDestination in bottomBarDestinations) {
                            CustomNavigationBar(navController)
                        }

                    }
                ){ innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = HomeScreenRoute
                    ){
                        composable<HomeScreenRoute>{
                            HomeScreen(
                                paddingValues = innerPadding,
                                navController = navController
                            )
                        }

                        composable<VersusScreenRoute>{
                            VersusScreen(
                                paddingValues = innerPadding,
                                navController = navController
                            )
                        }

                        composable <AccountScreenRoute>{
                            AccountScreen(
                                paddingValues = innerPadding,
                                navController = navController
                            )
                        }

                        composable <EditAccountScreenRoute>{
                            EditAccountScreen(
                                paddingValues = innerPadding,
                                navController = navController
                            )
                        }

                        composable <ReviewScreenRoute>{
                            ReviewScreen(
                                paddingValues = innerPadding,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProductReviewAppTheme {
        Greeting("Android")
    }
}