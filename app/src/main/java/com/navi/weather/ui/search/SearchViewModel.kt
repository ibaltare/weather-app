package com.navi.weather.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navi.weather.model.GeocodingElement
import com.navi.weather.model.GeocodingResult
import com.navi.weather.model.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel : ViewModel() {

    private val repository: SearchRepository = SearchRepository()
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState>
        get() = _state

    fun getSearchResult(city: String){
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            val response = withContext(Dispatchers.IO){
                repository.getSearchResult(city)
            }
            _state.value = UiState(places = response)
        }
    }

    fun onElementClicked(element: GeocodingElement){
        _state.value = _state.value?.copy(navigateTo = element)
    }

    data class UiState(
        val loading: Boolean = false,
        val places: GeocodingResult? = null,
        val navigateTo: GeocodingElement? = null
    )
}