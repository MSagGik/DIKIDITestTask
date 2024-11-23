package com.msaggik.featurehome.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.msaggik.featurehome.databinding.FragmentHomeBinding
import com.msaggik.featurehome.presentation.viewmodel.HomeViewModel
import com.msaggik.featurehome.presentation.viewmodel.state.HomeState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.homeStateLiveData.observe(viewLifecycleOwner) { homeState ->
            render(homeState)
        }
    }

    private fun render(homeState: HomeState) {
        when (homeState) {
            is HomeState.Loading -> {
                Log.e("homeState", "homeState loading")
            }
            is HomeState.Content -> {
                Log.e("homeState", "homeState homeResponse:\n${homeState.homeResponse}")
            }
            is HomeState.Error -> {
                Log.e("homeState", "homeState error: ${homeState.errorMessage}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
