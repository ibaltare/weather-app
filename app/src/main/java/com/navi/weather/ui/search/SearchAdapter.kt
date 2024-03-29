package com.navi.weather.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.navi.weather.R
import com.navi.weather.databinding.ItemListCityBinding
import com.navi.weather.model.GeocodingElement

class SearchAdapter(private val clickListener: (GeocodingElement) -> Unit) :
    ListAdapter<GeocodingElement, SearchAdapter.GeocodingViewHolder>(GeocodingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): GeocodingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_city, parent, false)
        val binding = ItemListCityBinding.bind(view)
        return GeocodingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GeocodingViewHolder, position: Int) {
        val element = getItem(position)
        holder.bind(element)
        holder.itemView.setOnClickListener { clickListener(element) }
    }

    class GeocodingViewHolder(private val binding: ItemListCityBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(element: GeocodingElement) {
            with(binding) {
                tvCity.text = element.name
                tvState.text = element.state
            }
        }
    }

    class GeocodingDiffCallback : DiffUtil.ItemCallback<GeocodingElement>() {
        override fun areItemsTheSame(oldItem: GeocodingElement, newItem: GeocodingElement): Boolean {
            return oldItem.name == newItem.name && oldItem.state == newItem.state
        }

        override fun areContentsTheSame(oldItem: GeocodingElement, newItem: GeocodingElement): Boolean {
            return oldItem == newItem
        }
    }
}