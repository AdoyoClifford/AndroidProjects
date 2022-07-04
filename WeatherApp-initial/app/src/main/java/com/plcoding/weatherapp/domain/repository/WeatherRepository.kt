package com.plcoding.weatherapp.domain.repository

import com.plcoding.weatherapp.domain.util.Resource
import com.plcoding.weatherapp.domain.weather.WeatherInfo

//Domain layer contains abstract data that is executed in  the data layer
interface WeatherRepository {
    suspend fun getWeatherData(lat: Double,long: Double): Resource<WeatherInfo>
}