package com.rahul.weatherapp.network

import com.rahul.weatherapp.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("forecast")
    fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") long: String,
        @Query("appid") appID: String
    ): Single<WeatherResponse>

}