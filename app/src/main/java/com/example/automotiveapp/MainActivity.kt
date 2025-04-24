package com.example.automotiveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.automotiveapp.presentations.brand.ui.BrandScreen
import com.example.automotiveapp.presentations.model.ModelsScreen
import com.example.automotiveapp.presentations.model.ModelsViewModel
import com.example.automotiveapp.ui.theme.AutomotiveAppTheme
import com.example.automotiveapp.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AutomotiveAppTheme {
                val navController = rememberNavController()

                Scaffold { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.BRAND,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        composable(Routes.BRAND) {
                            BrandScreen(navController = navController)
                        }
                        composable(
                            route = Routes.MODELS + "/{brandId}",
                            arguments = listOf(navArgument("brandId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val brandId = backStackEntry.arguments?.getInt("brandId") ?: 0
                            val viewModel: ModelsViewModel = hiltViewModel()
                            ModelsScreen(viewModel = viewModel, brandId = brandId)
                        }
                        composable(Routes.GENERATIONS) {
                            //  GenerationScreen()
                        }
                    }
                }
            }
        }
    }
}
