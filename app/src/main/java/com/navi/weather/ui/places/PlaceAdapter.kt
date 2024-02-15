package com.navi.weather.ui.places

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.navi.weather.R
import com.navi.weather.databinding.ItemListLocationBinding
import com.navi.weather.domain.CityWeather
import com.navi.weather.ui.common.loadUrl
import com.navi.weather.utils.Constants

class PlaceAdapter:
    ListAdapter<CityWeather, PlaceAdapter.PlaceViewHolder>(PlaceDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_location, parent, false)
        val binding = ItemListLocationBinding.bind(view)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val element = getItem(position)
        holder.bind(element)
    }

    class PlaceViewHolder(private val binding: ItemListLocationBinding):
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(element: CityWeather) {
            with(binding) {
                tvCity.text = element.city
                tvTemperature.text = "${element.temp}°"
                tvTempMaxMin.text = "${element.tempMax}°/${element.tempMin}°"
                val state = element.state ?: element.city
                tvCountry.text = "$state, ${element.country}"
                tvDescription.text = element.description
                imageView.loadUrl(Constants.ICON_URL+element.weatherIcon+Constants.ICON_EXTENSION)
            }
        }
    }

    class PlaceDiffCallback : DiffUtil.ItemCallback<CityWeather>() {
        override fun areItemsTheSame(oldItem: CityWeather, newItem: CityWeather): Boolean {
            return oldItem.city == newItem.city && oldItem.country == newItem.country
        }

        override fun areContentsTheSame(oldItem: CityWeather, newItem: CityWeather): Boolean {
            return oldItem == newItem
        }
    }
}