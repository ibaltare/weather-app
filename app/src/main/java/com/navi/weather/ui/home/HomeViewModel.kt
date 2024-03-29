package com.navi.weather.ui.home

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.navi.weather.domain.WeatherForecast
import com.navi.weather.model.LocationRepository
import com.navi.weather.model.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val weatherRepository: WeatherRepository,
                    private val locationRepository: LocationRepository) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        refresh()
    }

    private fun refresh(){
        viewModelScope.launch {
            val location = locationRepository.getCurrentLocation()
            if (location != null){
                _state.value = UiState(loading = true)
                _state.value = UiState(weatherForecast = weatherRepository.getWeatherForecast(location))
            }else {
                println("==Error Location **************")
            }
        }
    }
    data class UiState(
        val loading: Boolean = false,
        val weatherForecast: WeatherForecast? = null
    )
}

class HomeViewModelFactory(private val weatherRepository: WeatherRepository,
                           private val locationRepository: LocationRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(weatherRepository,locationRepository) as T
    }
}