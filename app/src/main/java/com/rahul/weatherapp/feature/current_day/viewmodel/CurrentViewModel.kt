package com.rahul.weatherapp.feature.current_day.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahul.weatherapp.model.WeatherResponse
import com.rahul.weatherapp.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CurrentViewModel : ViewModel() {
    private val apiService = ApiService()
    private val disposable = CompositeDisposable()
    private var _response = MutableLiveData<WeatherResponse>()

    var response: LiveData<WeatherResponse> = _response
    val loading = MutableLiveData<Boolean>()
    val loadingError = MutableLiveData<Boolean>()


    fun getWeatherResult(lat: String = "12.9716", long: String = "77.5946") {
        loading.value = true
        loadingError.value = false
        disposable.add(
            apiService.getWeather(lat, long)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherResponse>() {
                    override fun onSuccess(t: WeatherResponse) {
                        _response.value = t
                        loading.value = false
                        loadingError.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.i("Weather Error ","Error is ${e.toString()}")
                        loading.value = false
                        loadingError.value = true
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}