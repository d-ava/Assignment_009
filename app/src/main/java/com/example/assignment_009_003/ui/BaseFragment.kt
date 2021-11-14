package com.example.assignment_009_003.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

typealias inflate <T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(private val inflate: inflate<VB>) :
    Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!


    open var useSharedViewModel = false
    protected lateinit var viewModel: VM

    protected abstract fun getViewModel (): Class<VM>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    private fun init(){

        viewModel = if (useSharedViewModel){
            ViewModelProvider(requireActivity()).get(getViewModel())
        }else{
            ViewModelProvider(this).get(getViewModel())
        }

    }

    abstract fun start()

}