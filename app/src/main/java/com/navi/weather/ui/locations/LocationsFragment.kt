package com.navi.weather.ui.locations

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.navi.weather.R
import com.navi.weather.databinding.FragmentLocationsBinding

class LocationsFragment : Fragment() {

    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LocationsViewModel by viewModels()
    private val args: LocationsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*******************************/

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("==FRAGMENT","onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("==FRAGMENT","onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("==FRAGMENT","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("==FRAGMENT","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("==FRAGMENT","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("==FRAGMENT","onStop")
    }

}