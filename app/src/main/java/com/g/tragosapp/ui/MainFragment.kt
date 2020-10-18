package com.g.tragosapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.g.tragosapp.R
import com.g.tragosapp.core.Resource
import com.g.tragosapp.data.model.Cocktail
import com.g.tragosapp.databinding.FragmentMainBinding
import com.g.tragosapp.presentation.MainViewModel
import com.g.tragosapp.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main),
    MainAdapter.OnTragoClickListener {
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mainAdapter = MainAdapter(requireContext(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)

        binding.rvTragos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTragos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvTragos.adapter = mainAdapter

        binding.searchView.onQueryTextChanged {
            viewModel.setCocktail(it)
        }

        viewModel.fetchCocktailList.observe(viewLifecycleOwner) { result ->
            binding.progressBar.showIf { result is Resource.Loading }

            when (result) {
                is Resource.Loading -> {
                    binding.emptyContainer.root.hide()
                }
                is Resource.Success -> {
                    if (result.data.isEmpty()) {
                        binding.emptyContainer.root.show()
                        return@observe
                    }
                    mainAdapter.setCocktailList(result.data)
                    binding.emptyContainer.root.hide()
                }
                is Resource.Failure -> {
                    showToast("Ocurrió un error al traer los datos ${result.exception}")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favoritos -> {
                findNavController().navigate(R.id.action_mainFragment_to_favoritosFragment)
                false
            }
            else -> false
        }
    }

    override fun onCocktailClick(cocktail: Cocktail, position: Int) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToTragosDetalleFragment(
                cocktail
            )
        )
    }
}