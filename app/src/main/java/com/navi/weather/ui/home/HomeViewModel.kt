package com.navi.weather.ui.home

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.navi.weather.model.LocationRepository
import com.navi.weather.model.WeatherRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val weatherRepository: WeatherRepository,
                    private val locationRepository: LocationRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getWeather(){
        println("************** weather ++++++++++")
        viewModelScope.launch {
            val location = locationRepository.getCurrentLocation()
            if (location != null){
                val data = weatherRepository.getWeatherData(location)
                Log.d("==WEATHER", data.toString())
            }
        }

    }
}

class HomeViewModelFactory(private val weatherRepository: WeatherRepository,
                           private val locationRepository: LocationRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(weatherRepository,locationRepository) as T
    }
}