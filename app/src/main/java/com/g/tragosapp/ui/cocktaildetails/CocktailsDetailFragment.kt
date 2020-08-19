package com.g.tragosapp.ui.cocktaildetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.g.tragosapp.R
import com.g.tragosapp.data.model.Cocktail
import com.g.tragosapp.data.model.FavoritesEntity
import com.g.tragosapp.databinding.FragmentTragosDetalleBinding
import com.g.tragosapp.ui.viewmodel.MainViewModel
import com.g.tragosapp.utils.shortToast
import com.g.tragosapp.vo.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailsDetailFragment : Fragment() {

    private var _binding: FragmentTragosDetalleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var cocktail: Cocktail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            cocktail = it.getParcelable("drink")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTragosDetalleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(cocktail.image).centerCrop().into(binding.imgCocktail)
        binding.cocktailTitle.text = cocktail.name
        binding.cocktailDesc.text = cocktail.description

        viewModel.getFavoriteCocktails().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    when (result.data.contains(cocktail)) {
                        true -> {
                            binding.btnSaveCocktail.setOnClickListener {
                                viewModel.deleteCocktail(
                                    FavoritesEntity(
                                        cocktail.cocktailId,
                                        cocktail.image,
                                        cocktail.name,
                                        cocktail.description,
                                        cocktail.hasAlcohol
                                    )
                                ).observe(viewLifecycleOwner, Observer {
                                    if (it is Resource.Success) {
                                        context?.shortToast("Drink deleted !")
                                        findNavController().navigate(
                                            R.id.action_tragosDetalleFragment_self,
                                            Bundle().apply { putParcelable("drink", cocktail) }
                                        )
                                    }
                                })
                            }
                            binding.btnSaveCocktail.setImageDrawable(
                                getDrawable(requireContext(), R.drawable.ic_baseline_favorite_24)
                            )
                        }
                        false -> {
                            binding.btnSaveCocktail.setOnClickListener {
                                viewModel.saveCocktail(
                                    FavoritesEntity(
                                        cocktail.cocktailId,
                                        cocktail.image,
                                        cocktail.name,
                                        cocktail.description,
                                        cocktail.hasAlcohol
                                    )
                                )
                                context?.shortToast("Cocktail saved to favorites")
                                findNavController().navigate(
                                    R.id.action_tragosDetalleFragment_self,
                                    Bundle().apply { putParcelable("drink", cocktail) }
                                )
                            }
                            binding.btnSaveCocktail.setImageDrawable(
                                getDrawable(requireContext(), R.drawable.ic_baseline_save_24)
                            )
                        }
                    }
                }
                is Resource.Failure -> {
                    context?.shortToast("An error occurred ${result.exception}")
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}