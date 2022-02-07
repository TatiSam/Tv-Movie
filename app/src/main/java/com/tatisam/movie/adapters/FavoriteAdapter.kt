package com.tatisam.movie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tatisam.movie.MoviesApplication
import com.tatisam.movie.R
import com.tatisam.movie.databinding.FavoriteItemBinding
import com.tatisam.movie.models.Favorite
import com.tatisam.movie.utils.EXTRA_IMG_URL_W780
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteAdapter (private val favorites: List<Favorite>,
                       val callback: (Favorite) -> Unit) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FavoriteItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    @DelicateCoroutinesApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        with(favorites[position]) {
            if(this.favImage != null){
                val posterURL = EXTRA_IMG_URL_W780 + this.favImage
                Picasso.get().load(posterURL).into(holder.binding.ivFavorite)
            }

            holder.binding.tvFavTitle.text = this.favTitle
            holder.binding.ibFavorite.setImageResource(R.drawable.red_favorite)
            holder.binding.ibFavorite.setOnClickListener{
                GlobalScope.launch { MoviesApplication.favoriteRepository.deleteFavorite(this@with) }
            }
            holder.binding.root.setOnClickListener {
                callback(this)
            }
        }

    override fun getItemCount(): Int = favorites.size

    class ViewHolder(val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root)

}