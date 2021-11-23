package com.example.assignment_009_003.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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
import com.example.assignment_009_003.extensions.makeSnackbar
import kotlinx.coroutines.flow.collect

class ShopFragment :
    BaseFragment<ShopFragmentBinding, ShopViewModel>(ShopFragmentBinding::inflate) {

    override fun getViewModel() = ShopViewModel::class.java

    override var useSharedViewModel = false
    private lateinit var adapter: ShopAdapter

    override fun start() {

       setRecycler()
      setListener()
    }


    private fun setRecycler() {
        adapter = ShopAdapter()
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = GridLayoutManager(requireContext(),2)

    }

    private fun setObserverFromdb(){
        lifecycleScope.launchWhenStarted {
            viewModel.readFromdb.collect{
                if (it.isEmpty()){view?.makeSnackbar(getString(R.string.room_is_empty))}
                adapter.setData(it.toMutableList())
            }
        }
    }

    private fun setObserverFromJson(){
        viewModel.loadShop()
        lifecycleScope.launchWhenStarted {
            viewModel.shop.collect{
                adapter.setData(it)
            }
        }

    }

    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    private fun setListener(){
        binding.btnLoad.setOnClickListener {
            if (checkForInternet(requireContext())) {
                view?.makeSnackbar(getString(R.string.reading_from_json))
                setObserverFromJson()

            } else {
                setObserverFromdb()

                view?.makeSnackbar(getString(R.string.reading_from_db))

            }

        }
    }
}