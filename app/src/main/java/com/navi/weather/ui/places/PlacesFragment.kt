package com.navi.weather.ui.places

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.navi.weather.R
import com.navi.weather.databinding.FragmentPlacesBinding

class PlacesFragment : Fragment() {

    private var _binding: FragmentPlacesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlacesViewModel by viewModels()
    private val args: PlacesFragmentArgs by navArgs()

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
        setListeners()
    }

    private fun setListeners() {
        binding.efabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_locationsFragment_to_searchFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("==FRAGMENT","onResume")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}