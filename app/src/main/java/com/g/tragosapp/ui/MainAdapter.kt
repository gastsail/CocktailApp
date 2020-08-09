package com.g.tragosapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.g.tragosapp.R
import com.g.tragosapp.base.BaseViewHolder
import com.g.tragosapp.data.model.Drink
import kotlinx.android.synthetic.main.tragos_row.view.*

/**
 * Created by Gastón Saillén on 03 July 2020
 */

class MainAdapter(private val context: Context,private val itemClickLister:OnTragoClickListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var cocktailList = listOf<Drink>()

    interface OnTragoClickListener{
        fun onCocktailClick(drink: Drink, position:Int)
    }

    fun setCocktailList(cocktailList:List<Drink>){
        this.cocktailList = cocktailList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.tragos_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cocktailList .size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(cocktailList[position], position)
        }
    }

    private inner class MainViewHolder(itemView: View) : BaseViewHolder<Drink>(itemView) {
        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.image).centerCrop().into(itemView.img_cocktail)
            itemView.txt_titulo.text = item.name
            itemView.txt_descripcion.text = item.description
            itemView.setOnClickListener { itemClickLister.onCocktailClick(item,position) }
        }
    }
}