package com.rahul.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rahul.weatherapp.feature.current_day.viewmodel.CurrentViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: CurrentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[CurrentViewModel::class.java]
        viewModel.getWeatherResult()
        observeListResponse()

    }

    private fun observeListResponse() {
        viewModel.response.observe(this, Observer {

            Log.i("Weather Response","Weather Response = $it")

//            if (!it.isNullOrEmpty()) {
//                Log.i("Weather Response","Weather Response = $it")
//
//            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
            }

        })
        viewModel.loadingError.observe(this, Observer { isError ->
            isError?.let {
            }

        })
    }
}