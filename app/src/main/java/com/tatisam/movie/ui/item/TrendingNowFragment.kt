package com.tatisam.movie.ui.item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.tatisam.movie.R
import com.tatisam.movie.databinding.FragmentTrendingNowBinding
import com.tatisam.movie.models.Favorite
import com.tatisam.movie.models.TrendingWithGenres
import com.tatisam.movie.utils.*
import kotlinx.coroutines.DelicateCoroutinesApi

class TrendingNowFragment : Fragment() {
    private var _binding: FragmentTrendingNowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrendingNowBinding.inflate(inflater, container, false)
        return binding.root
    }

    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        if (args != null) {
            val trendingWithGenres: TrendingWithGenres = args.getParcelable<TrendingWithGenres>(
                EXTRA_TRENDING_NOW) as TrendingWithGenres
            val id = trendingWithGenres.trendingNow.trendingNowId
            val backdropURL = EXTRA_IMG_URL_ORIGINAL + trendingWithGenres.trendingNow.backdropPath
            Picasso.get().load(backdropURL).into(binding.ivTrendingBack)
            val posterURL = EXTRA_IMG_URL_W500+trendingWithGenres.trendingNow.posterPath
            Picasso.get().load(posterURL).into(binding.ivTrendingItem)
            binding.textTrendingScore.text = trendingWithGenres.trendingNow.voteAverage.toString()
            var title = ""
            var originalTitle = ""
            var date = ""
            when(trendingWithGenres.trendingNow.mediaType == EXTRA_MEDIA_TYPE_MOVIE){
                true -> {
                    title = trendingWithGenres.trendingNow.title.toString()
                    originalTitle = getString(R.string.original_title, trendingWithGenres.trendingNow.originalTitle)
                    date = getString(R.string.release_date, trendingWithGenres.trendingNow.releaseDate)
                }
                    false -> {
                        title = trendingWithGenres.trendingNow.name.toString()
                        originalTitle = getString(R.string.original_name, trendingWithGenres.trendingNow.originalName)
                        date = getString(R.string.first_air_date, trendingWithGenres.trendingNow.firstAirDate)
                    }
            }
            binding.textTrendingTitle.text = title
            binding.textMediaType.text = getString(R.string.media_type, trendingWithGenres.trendingNow.mediaType)
            binding.textTrendingOriginalTitle.text = originalTitle
            binding.textTrendingDate.text = date
            val genresStr = getGenresStr(trendingWithGenres.genres)
            binding.textTrendingGenres.text = genresStr
            binding.textTrendingOverview.text = trendingWithGenres.trendingNow.overview
            binding.textTrendingOriginalLanguage.text = getString(R.string.original_language, trendingWithGenres.trendingNow.originalLanguage)

            val favorite = Favorite(id, trendingWithGenres.trendingNow.posterPath, title, EXTRA_MEDIA_TYPE_TRENDING)
            val favBtn = binding.ibFavorite
            changeFavoriteIcon(favorite, favBtn)
            favBtn.setOnClickListener{ favBtnClick(favorite, favBtn) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}