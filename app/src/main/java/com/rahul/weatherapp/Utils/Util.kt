package com.rahul.weatherapp.Utils

import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class Util {

    //This is to convert Kelvin to Celsius and with Two Decimal points
    companion object {
        fun convertKelvinToCelsius(x: Double): String {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.DOWN
            val result = df.format(( x - 273.15f))
            return "$result Deg Celsius"
        }
    }
}