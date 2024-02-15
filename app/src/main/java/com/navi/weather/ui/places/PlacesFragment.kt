package com.navi.weather.ui.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.navi.weather.R
import com.navi.weather.databinding.FragmentPlacesBinding
import com.navi.weather.model.GeocodingElement
import com.navi.weather.ui.common.visible
import com.navi.weather.utils.Constants
import kotlinx.coroutines.launch

class PlacesFragment : Fragment() {

    private var _binding: FragmentPlacesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlacesViewModel by viewModels()
    private val adapter = PlaceAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlacesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        setListeners()
        setObservers()
    }

    private fun setup() {
        val swipeHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //adapter.remove(viewHolder.adapterPosition)
                viewModel.removePlace(viewHolder.adapterPosition)
            }
        })

        with(binding){
            rvPlaces.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvPlaces.adapter = adapter
            rvPlaces.setHasFixedSize(true)
            swipeHelper.attachToRecyclerView(rvPlaces)
        }
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    binding.progress.visible = state.loading
                    //state.places?.let (adapter::submitList )
                    state.places.apply {
                        adapter.submitList(this)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.
            getLiveData<GeocodingElement>(Constants.SEARCH_RESULT_KEY)?.
            observe(viewLifecycleOwner) { result ->
                viewModel.addPlace(result)
                findNavController().currentBackStackEntry?.savedStateHandle?.
                remove<GeocodingElement>(Constants.SEARCH_RESULT_KEY)
            }
    }

    private fun setListeners() {
        binding.efabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_placesFragment_to_searchFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}