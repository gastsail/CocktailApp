package com.g.tragosapp.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.g.tragosapp.R
import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.data.model.asDrinkEntity
import com.g.tragosapp.ui.MainAdapter
import com.g.tragosapp.ui.viewmodel.MainViewModel
import com.g.tragosapp.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favoritos.*

@AndroidEntryPoint
class FavoritosFragment : Fragment(), FavoritesAdapter.OnCocktailClickListener{

    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var favoritesAdapter:FavoritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesAdapter = FavoritesAdapter(requireContext(),this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favoritos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers(){
        viewModel.getTragosFavoritos().observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    favoritesAdapter.setCocktailList(result.data)
                }
                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "An error occurred ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun setupRecyclerView(){
        rv_tragos_favoritos.layoutManager = LinearLayoutManager(requireContext())
        rv_tragos_favoritos.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        rv_tragos_favoritos.adapter = favoritesAdapter
    }

    override fun onCocktailClick(drink: Drink, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("drink", drink)
        findNavController().navigate(R.id.action_favoritosFragment_to_tragosDetalleFragment, bundle)
    }

    override fun onCocktailDeleteLongClick(drink: DrinkEntity, position: Int) {
        viewModel.deleteDrink(drink).observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> { }
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "Drink deleted !", Toast.LENGTH_SHORT).show()
                    favoritesAdapter.setCocktailList(result.data)
                }
                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "An error occurred ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}