package com.g.tragosapp.ui.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.g.tragosapp.core.BaseViewHolder
import com.g.tragosapp.data.model.Cocktail
import com.g.tragosapp.databinding.TragosRowBinding

/**
 * Created by Gastón Saillén on 08 August 2020
 */
class FavoritesAdapter(
    private val context: Context,
    private val itemClickListener: OnCocktailClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var cocktailList = listOf<Cocktail>()

    interface OnCocktailClickListener {
        fun onCocktailClick(cocktail: Cocktail, position: Int)

        fun onCocktailLongClick(cocktail: Cocktail, position: Int)
    }

    fun setCocktailList(cocktailList: List<Cocktail>) {
        this.cocktailList = cocktailList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = TragosRowBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = MainViewHolder(itemBinding)

        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != NO_POSITION }
                ?: return@setOnClickListener

            itemClickListener.onCocktailClick(cocktailList[position], position)
        }

        holder.itemView.setOnLongClickListener {
            val position = holder.adapterPosition.takeIf { it != NO_POSITION }
                ?: return@setOnLongClickListener true

            itemClickListener.onCocktailLongClick(cocktailList[position], position)

            return@setOnLongClickListener true
        }

        return holder
    }

    override fun getItemCount(): Int = cocktailList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(cocktailList[position])
        }
    }

    private inner class MainViewHolder(
        private val binding: TragosRowBinding
    ) : BaseViewHolder<Cocktail>(binding.root) {
        override fun bind(item: Cocktail) = with(binding) {
            Glide.with(context)
                .load(item.image)
                .centerCrop()
                .into(imgCocktail)

            txtTitulo.text = item.name
            txtDescripcion.text = item.description
        }
    }
}