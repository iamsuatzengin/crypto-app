package com.suatzengin.whataboutcrypto.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.suatzengin.whataboutcrypto.databinding.FragmentSearchBinding
import com.suatzengin.whataboutcrypto.presentation.search.adapter.SearchRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: SearchRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.searchView.editText.setOnEditorActionListener { textView, actionId, keyEvent ->
            binding.searchBar.text = binding.searchView.text
            binding.tvDnme.text = binding.searchBar.text
            binding.searchView.hide()
            false
        }
        binding.searchView.editText.addTextChangedListener {
            if (it.isNullOrEmpty()) return@addTextChangedListener
            viewModel.search(it.toString())
        }
    }

    private fun setupRecyclerView() {
        val rvSearch = binding.rvSearch
        rvSearch.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = SearchRecyclerAdapter()
        rvSearch.adapter = adapter
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                adapter.submitList(state.list)
                if (state.message.isNotEmpty()) {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}