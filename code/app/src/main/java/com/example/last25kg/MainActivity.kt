package com.example.last25kg

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.last25kg.ui.screens.ActionSelectionScreen
import com.example.last25kg.ui.screens.ExportScreen
import com.example.last25kg.ui.screens.HomeScreen
import com.example.last25kg.ui.screens.MetricsDisplayScreen
import com.example.last25kg.ui.screens.TrajectoryDisplayScreen
import com.example.last25kg.ui.screens.VideoInputScreen
import com.example.last25kg.ui.theme.Last25kgTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Last25kgTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.fillMaxSize()
        ) {
            composable("home") {
                HomeScreen(navController)
            }
            composable("videoInput") {
                VideoInputScreen(navController)
            }
            composable("actionSelection") {
                ActionSelectionScreen(navController)
            }
            composable("trajectoryDisplay") {
                TrajectoryDisplayScreen(navController)
            }
            composable("metricsDisplay") {
                MetricsDisplayScreen(navController)
            }
            composable("export") {
                ExportScreen(navController)
            }
        }
    }
}