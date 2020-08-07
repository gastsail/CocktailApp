package com.g.tragosapp.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.g.tragosapp.AppDatabase
import com.g.tragosapp.R
import com.g.tragosapp.data.DataSourceImpl
import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.domain.RepoImpl
import com.g.tragosapp.ui.viewmodel.MainViewModel
import com.g.tragosapp.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : Fragment(), MainAdapter.OnTragoClickListener {

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.fetchTragosList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    empty_container.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    if (result.data.toMutableList().isEmpty()) {
                        empty_container.visibility = View.VISIBLE
                        return@Observer
                    }
                    empty_container.visibility = View.GONE
                    rv_tragos.adapter = MainAdapter(requireContext(), result.data.toMutableList(), this)
                }
                is Resource.Failure -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Ocurrió un error al traer los datos ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setTrago(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.favoritos -> {
                findNavController().navigate(R.id.action_mainFragment_to_favoritosFragment)
                false
            }
            else -> false
        }
    }

    override fun onTragoClick(drink: Drink, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("drink", drink)
        findNavController().navigate(R.id.action_mainFragment_to_tragosDetalleFragment, bundle)
    }

    private fun setupRecyclerView() {
        rv_tragos.layoutManager = LinearLayoutManager(requireContext())
        rv_tragos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }
}