package com.navi.weather.ui.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.navi.weather.domain.CityWeather
import com.navi.weather.domain.WeatherForecast
import com.navi.weather.ui.search.SearchViewModel

class PlacesViewModel : ViewModel() {

    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState>
        get() = _state

    fun addPlace(place: CityWeather) {

    }
    data class UiState(
        val loading: Boolean = false,
        val places: List<CityWeather>? = null
    )
}