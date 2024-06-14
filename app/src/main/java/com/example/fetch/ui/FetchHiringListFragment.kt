package com.example.fetch.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch.R
import com.example.fetch.adapter.HiringListAdapter
import com.example.fetch.databinding.FetchHiringListBinding
import com.example.fetch.viewmodel.MainViewModel

class FetchHiringListFragment: Fragment() {

    private lateinit var hiringListRecyclerView: RecyclerView
    private lateinit var hiringListAdapter: HiringListAdapter
    private lateinit var viewModel: MainViewModel
    private var _fetchHiringListBinding: FetchHiringListBinding? = null
    private val fetchHiringListBinding get() = _fetchHiringListBinding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _fetchHiringListBinding = FetchHiringListBinding.inflate(inflater,container, false)
        val view = fetchHiringListBinding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(view)
        initViewModel()
        initObservers()
    }

    private fun initRecyclerView(view: View){
        hiringListRecyclerView = view.findViewById(R.id.fetch_list_recycler_view)
        hiringListRecyclerView.layoutManager = LinearLayoutManager(context)
        hiringListAdapter = HiringListAdapter()
        hiringListRecyclerView.adapter = hiringListAdapter
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun initObservers(){
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, {hiringList ->
            if(hiringList != null){
                hiringListAdapter.setHiringList(hiringList)
                hiringListAdapter.notifyDataSetChanged()
            } else{
                Toast.makeText(context, "Error in loading data", Toast.LENGTH_LONG)
            }
        })
    }
}