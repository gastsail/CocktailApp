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

class MainAdapter(private val context: Context, private val tragosList: MutableList<Drink>,
            private val itemClickLister:OnTragoClickListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnTragoClickListener{
        fun onTragoClick(drink: Drink,position:Int)
    }

    fun deleteDrink(position: Int){
        tragosList.removeAt(position)
        notifyDataSetChanged()
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.tragos_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return tragosList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(tragosList[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Drink>(itemView) {
        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.imagen).centerCrop().into(itemView.img_trago)
            itemView.txt_titulo.text = item.nombre
            itemView.txt_descripcion.text = item.descripcion
            itemView.setOnClickListener { itemClickLister.onTragoClick(item,position) }
        }
    }
}