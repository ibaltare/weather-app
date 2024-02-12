package com.navi.weather.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.navi.weather.databinding.FragmentHomeBinding
import com.navi.weather.model.LocationRepository
import com.navi.weather.model.WeatherRepository
import com.navi.weather.ui.common.getResource
import com.navi.weather.ui.common.visible
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory(WeatherRepository(), LocationRepository(activity as AppCompatActivity)) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    @SuppressLint("SetTextI18n")
    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                homeViewModel.state.collect { state ->
                    binding.progress.visible = state.loading
                    state.weatherForecast?.let {
                        with(binding) {
                            tvCity.text = it.city
                            tvDescription.text = it.weatherDescription
                            tvTemperature.text = "${it.temperature}°"
                            tvHumidity.text = "${it.humidity}%"
                            tvVisibility.text = "${(it.visibility/1000)}Km"
                            tvWindSpeed.text = "${it.windSpeed}m/s"
                            val moment = if (it.weatherIcon.last() == 'n') "night" else "day"
                            val image = "bg_${moment}_${it.weatherMain.lowercase()}"
                            context?.getResource(image).let {
                                bgFragment.background = it
                            }

                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}