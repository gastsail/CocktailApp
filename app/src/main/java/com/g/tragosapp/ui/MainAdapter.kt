package com.g.tragosapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.g.tragosapp.base.BaseViewHolder
import com.g.tragosapp.data.model.Cocktail
import com.g.tragosapp.databinding.TragosRowBinding

/**
 * Created by Gastón Saillén on 03 July 2020
 */

class MainAdapter(private val context: Context,private val itemClickLister:OnTragoClickListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var cocktailList = listOf<Cocktail>()

    interface OnTragoClickListener{
        fun onCocktailClick(cocktail: Cocktail, position:Int)
    }

    fun setCocktailList(cocktailList:List<Cocktail>){
        this.cocktailList = cocktailList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = TragosRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return MainViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return cocktailList .size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(cocktailList[position], position)
        }
    }

    private inner class MainViewHolder(val binding: TragosRowBinding) : BaseViewHolder<Cocktail>(binding.root) {
        override fun bind(item: Cocktail, position: Int) = with(binding) {
            Glide.with(context).load(item.image).centerCrop().into(imgCocktail)
            txtTitulo.text = item.name
            txtDescripcion.text = item.description
            root.setOnClickListener { itemClickLister.onCocktailClick(item,position) }
        }
    }
}