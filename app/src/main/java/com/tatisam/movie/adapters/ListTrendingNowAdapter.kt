package com.tatisam.movie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tatisam.movie.databinding.ListMovieItemBinding
import com.tatisam.movie.models.Favorite
import com.tatisam.movie.models.TrendingWithGenres
import com.tatisam.movie.utils.*
import kotlinx.coroutines.DelicateCoroutinesApi

class ListTrendingNowAdapter (private val trendingWithGenres: List<TrendingWithGenres>,
                              val callback: (TrendingWithGenres) -> Unit) : RecyclerView.Adapter<ListTrendingNowAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListMovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    @DelicateCoroutinesApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        with(trendingWithGenres[position]) {
            val posterURL = EXTRA_IMG_URL_W780 + this.trendingNow.posterPath
            Picasso.get().load(posterURL).into(holder.binding.ivListMovie)
            holder.binding.textListItemScore.text = this.trendingNow.voteAverage.toString()

            val title = if(this.trendingNow.mediaType == EXTRA_MEDIA_TYPE_MOVIE)
                this.trendingNow.title.toString()
            else
                this.trendingNow.name.toString()
            val favorite = Favorite(this.trendingNow.trendingNowId, this.trendingNow.posterPath,
                                            title, EXTRA_MEDIA_TYPE_TRENDING)
            val favBtn = holder.binding.ibFavorite
            changeFavoriteIcon(favorite, favBtn)
            favBtn.setOnClickListener{ favBtnClick(favorite, favBtn) }

            holder.binding.root.setOnClickListener {
                callback(this)
            }
        }

    override fun getItemCount(): Int = trendingWithGenres.size

    class ViewHolder(val binding: ListMovieItemBinding) : RecyclerView.ViewHolder(binding.root)

}