package com.apolloagriculture.android.takehomeassignment.ui.weather

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apolloagriculture.android.takehomeassignment.R
import com.apolloagriculture.android.takehomeassignment.model.DayWeather
import com.apolloagriculture.android.takehomeassignment.model.Weather
import com.apolloagriculture.android.takehomeassignment.ui.theme.titleColor
import com.apolloagriculture.android.takehomeassignment.util.ConnectivityObserver
import com.apolloagriculture.android.takehomeassignment.util.ConnectivityObserver.Status.Available
import com.apolloagriculture.android.takehomeassignment.util.ConnectivityObserver.Status.Unavailable
import com.apolloagriculture.android.takehomeassignment.util.Resource


@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    connectivityObserver: ConnectivityObserver
) {

    val status by connectivityObserver.observe().collectAsState(initial = Unavailable)

    when (status) {
        Available -> {
            val weatherData = produceState<Resource<Weather?>?>(initialValue = Resource.Loading()) {
                value = viewModel.getWeatherData()
            }.value
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(32.dp, 16.dp),
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .border(BorderStroke(2.dp, SolidColor(MaterialTheme.colors.onSurface)))
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Weather", fontSize = 28.sp,
                            color = titleColor,
                            modifier = Modifier
                                .padding(start = 32.dp, top = 8.dp, bottom = 16.dp)
                                .fillMaxWidth()
                        )
                        when (weatherData) {
                            is Resource.Success -> {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    if (weatherData.data != null) {
                                        WeatherItem(weather = weatherData.data.today)
                                        WeatherItem(weather = weatherData.data.tomorrow)
                                        WeatherItem(weather = weatherData.data.dayAfterTomorrow)
                                    }
                                }
                            }

                            is Resource.Error -> {
                                Text(
                                    text = weatherData.message!!,
                                    color = Color.Red,
                                    textAlign = TextAlign.Center

                                )
                            }

                            is Resource.Loading -> {

                                CircularProgressIndicator(
                                    modifier = Modifier.size(50.dp),
                                    color = MaterialTheme.colors.primary,
                                    strokeWidth = 2.dp
                                )
                            }
                            else -> {}
                        }


                    }
                }
            }
        }
        Unavailable -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp, 16.dp),
            ) {
                Text(
                    text = "No internet connection",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )
            }
        }

    }
}


@Composable
fun WeatherItem(weather: DayWeather) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.rainy),
            contentDescription = "Weather",
            modifier = Modifier.width(40.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "${weather.lowTemp} - ${weather.highTemp} C",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = weather.description, fontSize = 12.sp, fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}


