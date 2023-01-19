package com.rahul.weatherapp.network

import com.google.gson.GsonBuilder
import com.rahul.weatherapp.model.WeatherResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val APIKEY = "542ffd081e67f4512b705f89d2a611b2"

class ApiService {

    private val NETWORK_CALL_TIMEOUT = 60
    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    val gson = GsonBuilder().create()

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            OkHttpClient.Builder()
                .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()
        )
        .build()
        .create(Api::class.java)

    fun getWeather(lat: String, long: String): Single<WeatherResponse>{
        return api.getWeather(lat,long, APIKEY)
    }
}