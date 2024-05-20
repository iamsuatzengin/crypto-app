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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.suatzengin.whataboutcrypto.data.remote.dto.coins.CoinItem
import com.suatzengin.whataboutcrypto.databinding.FragmentHomeBinding
import com.suatzengin.whataboutcrypto.presentation.MainActivity
import com.suatzengin.whataboutcrypto.presentation.home.adapters.CoinsRecyclerAdapter
import com.suatzengin.whataboutcrypto.presentation.home.adapters.TrendingCoinsAdapter
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

        viewModel.getCoinsAndTrending()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()

        collectData()

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getCoinsAndTrending()

            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setupRecyclerViews() {
        adapterCoins = CoinsRecyclerAdapter(onItemClick = ::onCoinItemClick)

        adapterTrending = TrendingCoinsAdapter()

        binding.rvCoinList.adapter = adapterCoins

        val rvTrending = binding.rvTrendingCoins

        rvTrending.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        rvTrending.adapter = adapterTrending
    }

    private fun collectData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    adapterCoins.submitList(state.coinList)

                    adapterTrending.submitList(state.trendingCoinList)

                    state.isLoading.let {
                        if (it) binding.progressBar.visibility = View.VISIBLE
                        else binding.progressBar.visibility = View.GONE
                    }

                    if (state.message.isNotEmpty()) {
                        Snackbar.make(
                            requireContext(),
                            binding.layoutId,
                            state.message,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun onCoinItemClick(coin: CoinItem) {
        val action = HomeFragmentDirections.actionHomePageToDetailFragment(
            coin.priceChangePercentage24h.toFloat(),
            coin.id
        )

        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
