package com.example.assignment_009_003.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment_009_003.R
import com.example.assignment_009_003.ShopAdapter
import com.example.assignment_009_003.databinding.ShopFragmentBinding
import kotlinx.coroutines.flow.collect

class ShopFragment :
    BaseFragment<ShopFragmentBinding, ShopViewModel>(ShopFragmentBinding::inflate) {

    override fun getViewModel() = ShopViewModel::class.java

    override var useSharedViewModel = false
    private lateinit var adapter: ShopAdapter

    override fun start() {

        setRecycler()
        setObserver()
    }


    private fun setRecycler() {
        adapter = ShopAdapter()
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun setObserver(){
        viewModel.loadShop()
        lifecycleScope.launchWhenStarted {
            viewModel.shop.collect{
                adapter.setData(it)
            }
        }

    }
}