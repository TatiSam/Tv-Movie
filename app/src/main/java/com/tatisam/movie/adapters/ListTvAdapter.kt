package com.tatisam.movie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tatisam.movie.databinding.ListMovieItemBinding
import com.tatisam.movie.models.Favorite
import com.tatisam.movie.models.TvWithGenres
import com.tatisam.movie.utils.*
import kotlinx.coroutines.DelicateCoroutinesApi

class ListTvAdapter (private val tvsWithGenres: List<TvWithGenres>,
                     val callback: (TvWithGenres) -> Unit) : RecyclerView.Adapter<ListTvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListMovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    @DelicateCoroutinesApi
    override fun onBindViewHolder(holder: ListTvAdapter.ViewHolder, position: Int) =
        with(tvsWithGenres[position]) {
            val posterURL = EXTRA_IMG_URL_W780 + this.tv.posterPath
            Picasso.get().load(posterURL).into(holder.binding.ivListMovie)
            holder.binding.textListItemScore.text = this.tv.voteAverage.toString()
            val favorite = Favorite(this.tv.tvId, this.tv.posterPath, this.tv.name, EXTRA_MEDIA_TYPE_TV)
            val favBtn = holder.binding.ibFavorite
            changeFavoriteIcon(favorite, favBtn)
            favBtn.setOnClickListener{ favBtnClick(favorite, favBtn) }

            holder.binding.root.setOnClickListener {
                callback(this)
            }
        }

    override fun getItemCount(): Int = tvsWithGenres.size

    class ViewHolder(val binding: ListMovieItemBinding) : RecyclerView.ViewHolder(binding.root)

}