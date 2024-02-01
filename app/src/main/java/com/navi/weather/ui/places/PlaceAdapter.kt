package com.navi.weather.ui.places

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.navi.weather.R
import com.navi.weather.databinding.ItemListLocationBinding
import com.navi.weather.domain.CityWeather

class PlaceAdapter:
    ListAdapter<CityWeather, PlaceAdapter.PlaceViewHolder>(PlaceAdapter.PlaceDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_location, parent, false)
        val binding = ItemListLocationBinding.bind(view)
        return PlaceAdapter.PlaceViewHolder(binding)
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
                tvTemperature.text = element.temp.toString()
                tvTempMaxMin.text = "${element.tempMax}/${element.tempMin}"
                val separator = if (element.state != null) ", " else ""
                tvCountry.text = "${element.state}$separator${element.country}"
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