package com.example.mvvm_example.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_example.data.offerElements.FirebaseCallbackOffer
import com.example.mvvm_example.data.offerElements.OfferData
import com.example.mvvm_example.databinding.ActivityOffersBinding
import com.example.mvvm_example.ui.view_models.OfferViewModel
import com.example.mvvm_example.utilities.InjectorUtils

class OffersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOffersBinding
    private lateinit var viewModel: OfferViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOffersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //-------ViewModel dla OffersActivity----------
        val factory = InjectorUtils.provideOfferViewModelFactory()
        viewModel = ViewModelProvider(this, factory)
            .get(OfferViewModel::class.java)

        val bundle: Bundle? = intent.extras

        viewModel.bundleFromActivity = bundle
        viewModel.setData()

        viewModel.getOffers(object : FirebaseCallbackOffer {
            override fun onCallbackOffer(list: List<OfferData>) {
                viewModel.offersList = list

                Log.d(TAG, viewModel.offersList.toString())
            }
        },
            viewModel.placeSelected.city,
            viewModel.placeSelected.country)





    }

    companion object{
        const val TAG = "OffersActivity"
    }
}