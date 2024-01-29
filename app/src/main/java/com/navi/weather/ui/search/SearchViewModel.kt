package com.navi.weather.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navi.weather.model.GeocodingElement
import com.navi.weather.model.GeocodingResult
import com.navi.weather.model.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val repository: SearchRepository = SearchRepository()
    private val _element = MutableLiveData<GeocodingResult>()
    val element: LiveData<GeocodingResult>
        get() = _element

    fun getSearchResult(city: String){
        viewModelScope.launch {
            val response = repository.getSearchResult(city)
            _element.value = response
            Log.d("==CITY",response.toString())
        }
    }
}