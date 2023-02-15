package com.example.mvvm_example.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_example.data.FirebaseCallbackPlace
import com.example.mvvm_example.data.Place
import com.example.mvvm_example.data.PlaceAdapter
import com.example.mvvm_example.databinding.FragmentSearchBinding
import com.example.mvvm_example.ui.view_models.PlaceViewModel
import com.example.mvvm_example.utilities.InjectorUtils
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {
    private val TAG = "Search Fragment"
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PlaceAdapter
    private var placesList: List<Place> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeUI()
    }

    private fun initializeUI() {
        //------Inizjalicacja obiektów XML----------
        val recyclerView = binding.searchRecyclerview
        val searchTextArea = binding.searchTextTravel

        //-----Inicjalizacja ViewModel----------
        val factory = InjectorUtils.providePlaceViewModelFactory()
        val viewModel = ViewModelProvider(this, factory)
            .get(PlaceViewModel::class.java)

        //-----Inicjalizacja RecyclerView---------
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(false)
        var adapter = PlaceAdapter(this, placesList)
        recyclerView.adapter = adapter
        registerForContextMenu(recyclerView)

        //------Pobranie danych o wycieczkach z bazy danych------------

        viewModel.getPlaces(object : FirebaseCallbackPlace {
            override fun onCallbackPlace(list: List<Place>) {
                placesList = list
            }
        })


        //--------Nasłuchiwanie zmian w polu tekstowym---------
        searchTextArea.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(textInput: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(textInput?.length!! >= 2)
                {
                    val specificTravels = viewModel.getSpecificPlaces(placesList, textInput.toString())
                    adapter.updatePlaces(specificTravels)
                }
                else
                {
                    adapter.updatePlaces(listOf())
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

    }

    fun selectedItem(place: Place)
    {
        val action = SearchFragmentDirections.actionSearchFragmentToHomeFragment().setPlaceData(place)
        findNavController().navigate(action)
    }
}