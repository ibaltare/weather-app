package com.navi.weather.ui.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navi.weather.domain.CityWeather
import com.navi.weather.model.GeocodingElement
import com.navi.weather.model.PlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlacesViewModel : ViewModel() {

    private val repository = PlaceRepository()
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun addPlace(place: GeocodingElement) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val response = withContext(Dispatchers.IO){
                repository.getWeatherForecastFromPlace(place)
            }
            _state.value?.places?.add(response)
            _state.value = state.value.copy(loading = false)
        }
    }

    fun removePlace(position: Int) {
        _state.value?.places?.removeAt(position)
        _state.value = state.value
    }

    data class UiState(
        val loading: Boolean = false,
        val places: MutableList<CityWeather> = mutableListOf()
    )

}