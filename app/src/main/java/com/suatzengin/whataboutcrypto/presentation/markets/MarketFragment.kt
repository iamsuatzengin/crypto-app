package com.suatzengin.whataboutcrypto.presentation.markets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.suatzengin.whataboutcrypto.databinding.FragmentMarketBinding
import com.suatzengin.whataboutcrypto.presentation.MainActivity
import com.suatzengin.whataboutcrypto.presentation.markets.adapter.MarketRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MarketFragment : Fragment() {

    private var _binding: FragmentMarketBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: MarketRecyclerView
    private val viewModel: MarketViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarketBinding.inflate(inflater, container, false)
        (activity as MainActivity).supportActionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() {
        val rvMarket = binding.rvMarket
        adapter = MarketRecyclerView()
        rvMarket.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvMarket.adapter = adapter
        val dividerItemDecoration =
            DividerItemDecoration(rvMarket.context, DividerItemDecoration.VERTICAL)
        rvMarket.addItemDecoration(dividerItemDecoration)
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    state.list.let {
                        adapter.submitList(it)
                    }
                    state.isLoading.let {
                        if (it) binding.progressBarMarket.visibility = View.VISIBLE
                        else binding.progressBarMarket.visibility = View.GONE
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