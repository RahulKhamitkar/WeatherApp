package com.rahul.weatherapp.feature.future_days.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.weatherapp.databinding.FragmentFutureBinding
import com.rahul.weatherapp.feature.current_day.adapter.FutureWeatherAdapter
import com.rahul.weatherapp.feature.current_day.viewmodel.SharedViewModel

class FutureFragment : Fragment() {

    private lateinit var futureWeatherAdapter: FutureWeatherAdapter
    lateinit var viewModel: SharedViewModel
    private lateinit var _binding: FragmentFutureBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFutureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        futureWeatherAdapter = FutureWeatherAdapter(arrayListOf())

        binding.rvFutureWeather.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = futureWeatherAdapter
        }
        observeFutureList()
    }

    private fun observeFutureList() {
        viewModel.response.observe(viewLifecycleOwner, Observer {
            futureWeatherAdapter.updateWeatherList(it.list)
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                binding.pgLoading.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loadingError.observe(viewLifecycleOwner, Observer { isError ->
            binding.pgLoading.visibility = View.GONE

            isError?.let {
                binding.errorMsg.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }
}