package com.g.tragosapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.g.tragosapp.R
import com.g.tragosapp.data.DataSource
import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.domain.RepoImpl
import com.g.tragosapp.ui.viewmodel.MainViewModel
import com.g.tragosapp.ui.viewmodel.VMFactory
import com.g.tragosapp.vo.Resource
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(),MainAdapter.OnTragoClickListener {

    private val viewModel by viewModels<MainViewModel> { VMFactory(RepoImpl(DataSource())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        viewModel.fetchTragosList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    rv_tragos.adapter = MainAdapter(requireContext(),result.data,this)
                }
                is Resource.Failure -> {
                    progressBar.visibility = View.GONE
                    Log.e("MainFragment", "onRetrofitRequest: ${result.exception}")
                    Toast.makeText(requireContext(), "Ocurrió un error al traer los datos ${result.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onTragoClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
        findNavController().navigate(R.id.tragosDetalleFragment,bundle)
    }

    private fun setupRecyclerView(){
        rv_tragos.layoutManager = LinearLayoutManager(requireContext())
        rv_tragos.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }
}