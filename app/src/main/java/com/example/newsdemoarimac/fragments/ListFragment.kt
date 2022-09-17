package com.example.newsdemoarimac.fragments

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsdemoarimac.R
import com.example.newsdemoarimac.adapter.TopNewsAdapter
import com.example.newsdemoarimac.callBack.AdapterCallBack
import com.example.newsdemoarimac.databinding.FragmentDetailsBinding
import com.example.newsdemoarimac.databinding.FragmentListBinding
import com.example.newsdemoarimac.models.Article
import com.example.newsdemoarimac.util.Constants
import com.example.newsdemoarimac.viewModels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), AdapterCallBack, View.OnClickListener {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentListBinding
    private val viewModel: NewsViewModel by viewModels()
    private var allNewsList: MutableList<Article> = mutableListOf()
    private var pageNumber = 1
    private var query = "trump"
    private var isSearch = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)

        arguments.let {
            if (it != null){
                isSearch = it.getBoolean(Constants.SEARCH)
                query = it.getString(Constants.QUERY)!!
            }

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        if (isSearch) {
            binding.autoCompleteTextView.setText(query)
            binding.autoCompleteTextView.visibility = View.VISIBLE
        }

        callNewsData(pageNumber, query)

        binding.ibBack.setOnClickListener(this)

        val topNewsAdapter = TopNewsAdapter(listOf(), requireContext())
        val layoutManagerRecycle = LinearLayoutManager(context)
        binding.rvList.apply {
            adapter = topNewsAdapter.apply {
                callBack = this@ListFragment
            }
            isNestedScrollingEnabled = false
            layoutManager = layoutManagerRecycle
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (layoutManagerRecycle.findLastVisibleItemPosition() == allNewsList.size - 1) {
                        pageNumber += 1
                        callNewsData(pageNumber, query)
                    }

                }
            })

        }

        viewModel.newsResponse.observe(viewLifecycleOwner) {
            if (pageNumber == 1){
                allNewsList.clear()
            }
            allNewsList.addAll(it.articles)
            topNewsAdapter.newsList = allNewsList
            topNewsAdapter.notifyDataSetChanged()
        }


        searchData()
    }

    private fun searchData() {
        binding.autoCompleteTextView.setOnEditorActionListener(object :
            TextView.OnEditorActionListener {

            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (v != null) {
                    if (v.text.trim().isNotEmpty()) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                            pageNumber = 1
                            callNewsData(pageNumber,v.text.toString())
                            return true
                        }
                    }
                }
                return false
            }
        })
    }

    private fun callNewsData(pageNumber: Int, query: String) {
        val paramsTopNews: MutableMap<String, Any> = HashMap()
        paramsTopNews["q"] = query
        paramsTopNews["pageSize"] = "10"
        paramsTopNews["page"] = pageNumber.toString()
        paramsTopNews["apiKey"] = Constants.API_KEY
        viewModel.getAllNewsData(paramsTopNews)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ListFragment()
    }

    override fun onItemClick(item: Article) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.ARTICLE, item)
        navController.navigate(R.id.action_listFragment_to_detailsFragment, bundle)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ib_back -> {
                navController.popBackStack()
            }
        }
    }
}