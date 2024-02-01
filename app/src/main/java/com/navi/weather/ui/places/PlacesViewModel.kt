package com.navi.weather.ui.places

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navi.weather.domain.CityWeather
import com.navi.weather.domain.WeatherForecast
import com.navi.weather.model.GeocodingElement
import com.navi.weather.model.PlaceRepository
import com.navi.weather.ui.search.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlacesViewModel : ViewModel() {

    private val repository = PlaceRepository()
    private val _places: MutableList<CityWeather> = mutableListOf()
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState>
        get() = _state

    fun addPlace(place: GeocodingElement?) {
        if (place != null){
            viewModelScope.launch {
                _state.value = UiState(loading = true)
                val response = withContext(Dispatchers.IO){
                    repository.getWeatherForecastFromPlace(place)
                }
                _places.add(response)
                _state.value = UiState(loading = false, places = _places)
                Log.d("==_places", _places.toString())
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val places: List<CityWeather>? = null
    )

    override fun onCleared() {
        Log.d("==CLEARED",_places.toString())
        super.onCleared()
    }
}