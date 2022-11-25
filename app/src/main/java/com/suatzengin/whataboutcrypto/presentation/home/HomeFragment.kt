package com.suatzengin.whataboutcrypto.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.suatzengin.whataboutcrypto.databinding.FragmentHomeBinding
import com.suatzengin.whataboutcrypto.domain.model.HomeType
import com.suatzengin.whataboutcrypto.presentation.MainActivity
import com.suatzengin.whataboutcrypto.presentation.home.adapters.CoinsRecyclerAdapter
import com.suatzengin.whataboutcrypto.presentation.home.adapters.TrendingCoinsAdapter
import com.suatzengin.whataboutcrypto.util.UiEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapterCoins: CoinsRecyclerAdapter
    private lateinit var adapterTrending: TrendingCoinsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.hide()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        observeData()
    }

    private fun setupRecyclerViews() {
        adapterCoins = CoinsRecyclerAdapter()
        adapterTrending = TrendingCoinsAdapter()
        binding.rvCoinList.adapter = adapterCoins
        val rvTrending = binding.rvTrendingCoins
        rvTrending.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvTrending.adapter = adapterTrending
    }

    private fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collect { event ->
                when (event) {
                    is UiEvent.ShowSnackbar -> {
                        Snackbar.make(
                            requireContext(),
                            binding.layoutId,
                            event.message,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    state.list.forEach { homeType ->
                        when (homeType) {
                            is HomeType.TrendingCoins -> {
                                adapterTrending.submitList(homeType.coins)
                                binding.trendingDescription.visibility = View.VISIBLE
                            }
                            is HomeType.CoinList -> {
                                adapterCoins.submitList(homeType.coins)
                                binding.tvRankings.visibility = View.VISIBLE
                            }
                        }
                    }
                    state.isLoading.let {
                        if (it) binding.progressBar.visibility = View.VISIBLE
                        else binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}