package com.apolloagriculture.android.takehomeassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apolloagriculture.android.takehomeassignment.ui.firstScreen.FirstScreen
import com.apolloagriculture.android.takehomeassignment.ui.theme.AndroidtakehomeassignmentTheme
import com.apolloagriculture.android.takehomeassignment.ui.weather.WeatherScreen
import com.apolloagriculture.android.takehomeassignment.ui.weather.WeatherViewModel
import com.apolloagriculture.android.takehomeassignment.util.ConnectivityObserver
import com.apolloagriculture.android.takehomeassignment.util.NetworkConnectivityObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)

        setContent {
            AndroidtakehomeassignmentTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "first_screen") {
                        composable("first_screen") {
                            FirstScreen(navController = navController)
                        }
                        composable("weather_screen") {
                            WeatherScreen(navController= navController, connectivityObserver = connectivityObserver)
                        }
                    }
                }
            }
        }
    }


}