package com.example.newsdemoarimac.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsdemoarimac.R
import com.example.newsdemoarimac.adapter.BreakNewsAdapter
import com.example.newsdemoarimac.adapter.TopNewsAdapter
import com.example.newsdemoarimac.callBack.AdapterCallBack
import com.example.newsdemoarimac.databinding.FragmentDashboardBinding
import com.example.newsdemoarimac.models.Article
import com.example.newsdemoarimac.util.Constants
import com.example.newsdemoarimac.viewModels.NewsViewModel
import com.example.newsdemoarimac.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(),AdapterCallBack, View.OnClickListener {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentDashboardBinding
    private val viewModel:NewsViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater,container,false)

        arguments.let {
            if (it != null){
                val userName = it.getString(Constants.USERNAME)
                if (userName != null) {
                    userViewModel.setLogin(userName,true)
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val params: MutableMap<String,Any> = HashMap()
        params["q"] = "trump"
        params["pageSize"] = "10"
        params["page"] = "1"
        params["apiKey"] = Constants.API_KEY
        viewModel.getAllNewsData(params)

        val paramsTopNews: MutableMap<String,Any> = HashMap()
        paramsTopNews["country"] = "us"
        paramsTopNews["pageSize"] = "10"
        paramsTopNews["page"] = "1"
        paramsTopNews["apiKey"] = Constants.API_KEY
        viewModel.getAllTopNewsData(paramsTopNews)

        navController = Navigation.findNavController(view)

        val breakNewsAdapter = BreakNewsAdapter(listOf(),requireContext())
        binding.rvBreak.apply {
            adapter = breakNewsAdapter.apply {
                callBack = this@DashboardFragment
            }
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        }

        val topNewsAdapter = TopNewsAdapter(listOf(),requireContext())

        binding.rvTop.apply {
            adapter = topNewsAdapter.apply {
                callBack = this@DashboardFragment
            }
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)

        }

        viewModel.newsResponse.observe(viewLifecycleOwner) { response ->
            topNewsAdapter.newsList = response.articles
            topNewsAdapter.notifyDataSetChanged()
        }

        viewModel.topNewsResponse.observe(viewLifecycleOwner) {
            breakNewsAdapter.newsList = it.articles
            breakNewsAdapter.notifyDataSetChanged()
        }

        binding.tvViewAll.setOnClickListener(this)

        //Search data
        searchJobList()


    }


    companion object {
        @JvmStatic
        fun newInstance() =
            DashboardFragment()

    }

    private fun searchJobList(){

        binding.autoCompleteTextView.setOnEditorActionListener(object : TextView.OnEditorActionListener {

            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (v != null) {
                    if (v.text.trim().isNotEmpty()) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                            val bundle = Bundle()
                            bundle.putBoolean(Constants.SEARCH,true)
                            bundle.putString(Constants.QUERY,v.text.toString())
                            navController.navigate(R.id.action_dashboardFragment_to_listFragment,bundle)

                            return true
                        }
                    }
                }
                return false
            }
        })
        binding.autoCompleteTextView.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    if(s.isEmpty()) {
//                        if(searched) {
//                            items.clear()
//                            itemsId.clear()
//                            adapter!!.notifyDataSetChanged()
//                            appUtils!!.showProgressDialog(DialogInterface.OnCancelListener { dialog: DialogInterface ->
//                                dialog.dismiss()
//                            })
//                            pageNumber = 1
//                            jobsRemoved = 0
//                            find_trips.searchkey = ""
//                            callNewJobsWebService(pageNumber)
//                        }
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    override fun onItemClick(item: Article) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.ARTICLE,item)
        navController.navigate(R.id.action_dashboardFragment_to_detailsFragment,bundle)
    }

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.tv_view_all -> {
                navController.navigate(R.id.action_dashboardFragment_to_listFragment)

            }
        }
    }
}