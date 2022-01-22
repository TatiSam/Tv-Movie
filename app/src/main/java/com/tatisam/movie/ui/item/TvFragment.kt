package com.tatisam.movie.ui.item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.tatisam.movie.R
import com.tatisam.movie.databinding.FragmentTvBinding
import com.tatisam.movie.models.Favorite
import com.tatisam.movie.models.TvWithGenres
import com.tatisam.movie.utils.*
import kotlinx.coroutines.DelicateCoroutinesApi

class TvFragment : Fragment() {
    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        if (args != null) {
            val popularTv: TvWithGenres = args.getParcelable<TvWithGenres>(EXTRA_POPULAR_TV) as TvWithGenres
            val backdropURL = EXTRA_IMG_URL_ORIGINAL +popularTv.tv.backdropPath
            Picasso.get().load(backdropURL).into(binding.ivTvBack)
            val posterURL = EXTRA_IMG_URL_W500+popularTv.tv.posterPath
            Picasso.get().load(posterURL).into(binding.ivTvItem)
            binding.textTvScore.text = popularTv.tv.voteAverage.toString()
            binding.textTvName.text = popularTv.tv.name
            val genresStr = getGenresStr(popularTv.genres)
            binding.textTvGenres.text = genresStr
            binding.textFirstAirDate.text = getString(R.string.first_air_date, popularTv.tv.firstAirDate)
            binding.textTvOverview.text = popularTv.tv.overview
            binding.textTvOriginalName.text = getString(R.string.original_name, popularTv.tv.originalName)
            binding.textTvOriginalLanguage.text = getString(R.string.original_language, popularTv.tv.originalLanguage)

            val favorite = Favorite(popularTv.tv.tvId, popularTv.tv.posterPath,
                popularTv.tv.name, EXTRA_MEDIA_TYPE_TV)
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