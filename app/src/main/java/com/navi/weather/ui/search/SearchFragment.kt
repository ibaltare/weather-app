package com.navi.weather.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.navi.weather.databinding.FragmentSearchBinding
import com.navi.weather.model.GeocodingElement
import com.navi.weather.ui.common.hideKeyboard
import com.navi.weather.ui.common.showKeyboard
import com.navi.weather.ui.common.visible
import com.navi.weather.utils.Constants
import kotlinx.coroutines.launch

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
            tiSearch.showKeyboard()
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    binding.progress.visible = state.loading
                    //state.places?.let (adapter::submitList )
                    state.places?.let {
                        adapter.submitList(it)
                        if (it.isNotEmpty()) hideKeyboard()
                    }
                    state.navigateTo?.let {
                        navigateTo(it)
                    }
                }
            }
        }
    }

    private fun navigateTo(element: GeocodingElement) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            Constants.SEARCH_RESULT_KEY,
            element
        )
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}