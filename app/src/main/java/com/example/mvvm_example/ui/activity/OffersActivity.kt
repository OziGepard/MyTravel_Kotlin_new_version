package com.example.mvvm_example.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_example.data.FirebaseCallbackOffer
import com.example.mvvm_example.data.OfferAdapter
import com.example.mvvm_example.data.OfferData
import com.example.mvvm_example.databinding.ActivityOffersBinding
import com.example.mvvm_example.ui.view_models.OfferViewModel
import com.example.mvvm_example.utilities.InjectorUtils
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class OffersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOffersBinding
    private lateinit var viewModel: OfferViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOffersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeData()
        initializeUI()

    }

    private fun initializeData() {
        //-------ViewModel dla OffersActivity----------
        val factory = InjectorUtils.provideOfferViewModelFactory()
        viewModel = ViewModelProvider(this, factory)
            .get(OfferViewModel::class.java)

        //-------Dane dla viewModel----------
        val bundle: Bundle? = intent.extras

        viewModel.bundleFromActivity = bundle
        viewModel.setData()


    }

    private fun initializeUI() {
        val recyclerView = binding.travelRecyclerview
        var adapter: OfferAdapter?

        viewModel.getOffers(object : FirebaseCallbackOffer {
            override fun onCallbackOffer(list: List<OfferData>) {
                viewModel.offersList = list

                adapter = OfferAdapter(viewModel.offersList)
                recyclerView.adapter = adapter

                Log.d(TAG, viewModel.offersList.toString())
            }
        },
            viewModel.placeSelected.city,
            viewModel.placeSelected.country)


        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(false)
        registerForContextMenu(recyclerView)

    }

    suspend fun initRecycler() = coroutineScope {

    }

    companion object{
        const val TAG = "OffersActivity"
    }
}