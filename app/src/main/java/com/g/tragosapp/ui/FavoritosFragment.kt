package com.g.tragosapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.g.tragosapp.AppDatabase
import com.g.tragosapp.R
import com.g.tragosapp.data.DataSource
import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.domain.RepoImpl
import com.g.tragosapp.ui.viewmodel.MainViewModel
import com.g.tragosapp.ui.viewmodel.VMFactory
import com.g.tragosapp.vo.Resource
import kotlinx.android.synthetic.main.fragment_favoritos.*

class FavoritosFragment : Fragment(),MainAdapter.OnTragoClickListener {

    private lateinit var adapter:MainAdapter

    private val viewModel by activityViewModels<MainViewModel> { VMFactory(
        RepoImpl(
            DataSource(
        AppDatabase.getDatabase(requireActivity().applicationContext))
        )
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    val lista = result.data.map {
                        Drink(it.tragoId,it.imagen,it.nombre,it.descripcion,it.hasAlcohol)
                    }.toMutableList()

                    adapter = MainAdapter(requireContext(), lista,this)
                    rv_tragos_favoritos.adapter = adapter
                }
                is Resource.Failure -> {

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