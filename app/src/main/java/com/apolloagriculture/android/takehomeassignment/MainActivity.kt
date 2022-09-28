package com.apolloagriculture.android.takehomeassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apolloagriculture.android.takehomeassignment.ui.firstScreen.FirstScreen
import com.apolloagriculture.android.takehomeassignment.ui.theme.AndroidtakehomeassignmentTheme
import com.apolloagriculture.android.takehomeassignment.ui.theme.Purple700
import com.apolloagriculture.android.takehomeassignment.ui.weather.WeatherScreen
import com.apolloagriculture.android.takehomeassignment.util.ConnectivityObserver
import com.apolloagriculture.android.takehomeassignment.util.NetworkConnectivityObserver
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)

        setContent {
            AndroidtakehomeassignmentTheme {

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = getString(R.string.app_name), fontSize = 18.sp)
                            },
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = Color.White,
                            elevation = 10.dp
                        )
                    }, content = {
                        val systemUiController = rememberSystemUiController()
                        SideEffect {
                            systemUiController.setStatusBarColor(
                                color = Purple700,
                                darkIcons = false
                            )
                        }

                        Surface(
                            color = MaterialTheme.colors.background,
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            val navController = rememberNavController()

                            NavHost(
                                navController = navController,
                                startDestination = "first_screen"
                            ) {
                                composable("first_screen") {
                                    FirstScreen(navController = navController)
                                }
                                composable("weather_screen") {
                                    WeatherScreen(
                                        connectivityObserver = connectivityObserver
                                    )
                                }
                            }
                        }
                    }
                )

            }

        }
    }


}