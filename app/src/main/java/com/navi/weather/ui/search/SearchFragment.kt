package com.navi.weather.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.navi.weather.databinding.FragmentSearchBinding
import com.navi.weather.model.GeocodingElement
import com.navi.weather.ui.common.visible

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val adapter = SearchAdapter {viewModel.onElementClicked(it)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        setListeners()
        setObservers()
    }


    private fun setup() {
        with(binding){
            rvSearch.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvSearch.adapter = adapter
            rvSearch.setHasFixedSize(true)
        }
    }

    private fun setListeners() {
        binding.tiSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if(!binding.tiSearch.text.isNullOrBlank()){
                    viewModel.getSearchResult(binding.tiSearch.text.toString())
                }
            }
            true
        }
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.progress.visible = state.loading
            state.places?.let (adapter::submitList )
            state.navigateTo?.let {
                navigateTo(it)
            }
        }
    }

    private fun navigateTo(element: GeocodingElement) {
        val action = SearchFragmentDirections.actionSearchFragmentToLocationsFragment(element)
        findNavController().navigate(action)

        findNavController().previousBackStackEntry?.savedStateHandle?.set("key", element)
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}