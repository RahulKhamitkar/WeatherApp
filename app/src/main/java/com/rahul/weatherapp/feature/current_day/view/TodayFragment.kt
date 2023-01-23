package com.rahul.weatherapp.feature.current_day.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rahul.weatherapp.R
import com.rahul.weatherapp.Utils.NetworkConnection
import com.rahul.weatherapp.Utils.Util
import com.rahul.weatherapp.databinding.FragmentTodayBinding
import com.rahul.weatherapp.feature.current_day.viewmodel.SharedViewModel


class TodayFragment : Fragment() {

    lateinit var viewModel: SharedViewModel
    private lateinit var _binding: FragmentTodayBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        NetworkConnection.isNetworkAvailable(requireActivity().application)
        viewModel.getWeatherResult()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeListResponse()
    }

    private fun observeListResponse() {
        viewModel.response.observe(viewLifecycleOwner, Observer {

//            Log.i("Weather Response", "Weather Response = $it")
            binding.tvCity.text = it?.city?.name.toString()

            //This is to display Temperature in Celsius
            binding.tvTemp.text =
                it.list[0].main?.temp?.let { temp -> Util.convertKelvinToCelsius(temp).toString() }

            //This is to display Max temp in C
            binding.tvMaxTemp.text = it.list[0].main?.tempMax?.let { maxTemp ->
                Util.convertKelvinToCelsius(maxTemp).toString()
            }

            //This is to Display Min Temp in C
            binding.tvMin.text = it.list[0].main?.tempMin?.let { minTemp ->
                Util.convertKelvinToCelsius(minTemp).toString()
            }

            //This is to display Respective weather Image
            when (it.list[0].weather[0].main.toString()) {
                "Rain" -> {
                    setImage(R.drawable.ic_rain, "Rainy")
                }
                "Clouds" -> {
                    setImage(R.drawable.ic_clound, "Cloudy")
                }
                "Clear" -> {
                    setImage(R.drawable.ic_sun, "Sunny")
                }
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                binding.pbToday.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loadingError.observe(viewLifecycleOwner, Observer { isError ->
            binding.pbToday.visibility = View.GONE

            isError?.let {
                binding.tvErrorMsg.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }

    //Function to Set Image and weather Type
    private fun setImage(image: Int, type: String) {
        binding.ivWeatherType.setImageResource(image)
        binding.tvWeatherType.text = type
    }
}