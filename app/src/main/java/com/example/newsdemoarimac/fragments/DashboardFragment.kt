package com.example.newsdemoarimac.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsdemoarimac.R
import com.example.newsdemoarimac.adapter.BreakNewsAdapter
import com.example.newsdemoarimac.adapter.TopNewsAdapter
import com.example.newsdemoarimac.databinding.FragmentDashboardBinding
import com.example.newsdemoarimac.viewModels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentDashboardBinding
    private val viewModel:NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.rvBreak.apply {
            adapter = BreakNewsAdapter()
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        }

        val topNewsAdapter = TopNewsAdapter(listOf())

        binding.rvTop.apply {
            adapter = topNewsAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)

        }

        viewModel.newsResponse.observe(viewLifecycleOwner) { response ->
            topNewsAdapter.newsList = response.articles
            topNewsAdapter.notifyDataSetChanged()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DashboardFragment()

    }
}