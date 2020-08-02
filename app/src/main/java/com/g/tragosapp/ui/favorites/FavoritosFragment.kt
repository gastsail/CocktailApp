package com.g.tragosapp.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.g.tragosapp.R
import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.data.model.asDrinkList
import com.g.tragosapp.ui.MainAdapter
import com.g.tragosapp.ui.viewmodel.MainViewModel
import com.g.tragosapp.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favoritos.*

@AndroidEntryPoint
class FavoritosFragment : Fragment(),
    MainAdapter.OnTragoClickListener {

    private lateinit var adapter: MainAdapter
    private val viewModel by activityViewModels<MainViewModel>()

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
                    val lista = result.data.asDrinkList()

                    adapter = MainAdapter(
                        requireContext(),
                        lista,
                        this
                    )
                    rv_tragos_favoritos.adapter = adapter
                }
                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Ocurrió un error ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun setupRecyclerView(){
        rv_tragos_favoritos.layoutManager = LinearLayoutManager(requireContext())
        rv_tragos_favoritos.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }

    override fun onTragoClick(drink: Drink,position:Int) {
        viewModel.deleteDrink(DrinkEntity(drink.tragoId,drink.imagen,drink.nombre,drink.descripcion,drink.hasAlcohol))
        adapter.deleteDrink(position)
        Toast.makeText(requireContext(), "Se borró el trago favorito", Toast.LENGTH_SHORT).show()
    }
}