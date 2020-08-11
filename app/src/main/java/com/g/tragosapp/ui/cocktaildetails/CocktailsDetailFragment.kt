package com.g.tragosapp.ui.cocktaildetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.g.tragosapp.R
import com.g.tragosapp.data.model.Cocktail
import com.g.tragosapp.data.model.FavoritesEntity
import com.g.tragosapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tragos_detalle.*

@AndroidEntryPoint
class CocktailsDetailFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_tragos_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(cocktail.image).centerCrop().into(img_cocktail)
        cocktail_title.text = cocktail.name
        cocktail_desc.text = cocktail.description

        btn_save_cocktail.setOnClickListener {
            viewModel.saveCocktail(FavoritesEntity(cocktail.cocktailId,cocktail.image,cocktail.name,cocktail.description,cocktail.hasAlcohol))
            Toast.makeText(requireContext(), "Cocktail saved to favorites", Toast.LENGTH_SHORT)
                .show()
        }
    }
}