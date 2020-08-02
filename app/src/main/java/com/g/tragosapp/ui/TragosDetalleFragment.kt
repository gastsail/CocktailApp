package com.g.tragosapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.g.tragosapp.AppDatabase
import com.g.tragosapp.R
import com.g.tragosapp.data.DataSourceImpl
import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.domain.RepoImpl
import com.g.tragosapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tragos_detalle.*

@AndroidEntryPoint
class TragosDetalleFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable("drink")!!
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
        Glide.with(requireContext()).load(drink.imagen).centerCrop().into(img_trago)
        trago_title.text = drink.nombre
        trago_desc.text = drink.descripcion
        if(drink.hasAlcohol == "Non_Alcoholic"){
            txt_has_alcohol.text = "Bebida sin alcohol"
        }else{
            txt_has_alcohol.text = "Bebida con alcohol"
        }

        btn_guardar_trago.setOnClickListener {
            viewModel.guardarTrago(DrinkEntity(drink.tragoId,drink.imagen,drink.nombre,drink.descripcion,drink.hasAlcohol))
            Toast.makeText(requireContext(), "Se guardó el trago a favoritos", Toast.LENGTH_SHORT)
                .show()
        }
    }
}