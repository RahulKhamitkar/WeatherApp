package com.rahul.weatherapp.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

class NetworkConnection {

    companion  object{
        fun isNetworkAvailable(context: Context){
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                    //Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                    //Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                }
            } else {
                // not connected to the internet
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show()

            }
        }
    }
}