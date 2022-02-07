package com.tatisam.movie.ui.item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.tatisam.movie.R
import com.tatisam.movie.databinding.MovieFragmentBinding
import com.tatisam.movie.models.Favorite
import com.tatisam.movie.models.MoviesWithGenres
import com.tatisam.movie.utils.*
import kotlinx.coroutines.DelicateCoroutinesApi

class MovieFragment : Fragment() {

    private var _binding: MovieFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        if (args != null) {
            val movieWithGenres: MoviesWithGenres = args
                .getParcelable<MoviesWithGenres>(EXTRA_POPULAR_MOVIE) as MoviesWithGenres
            val backdropURL = EXTRA_IMG_URL_ORIGINAL + movieWithGenres.movie.backdropPath
            Picasso.get().load(backdropURL).into(binding.ivBack)
            val posterURL = EXTRA_IMG_URL_W500 + movieWithGenres.movie.posterPath
            Picasso.get().load(posterURL).into(binding.ivMovieItem)
            binding.textMovieTitle.text = movieWithGenres.movie.title
            binding.textMovieScore.text = movieWithGenres.movie.voteAverage.toString()
            val genresStr = getGenresStr(movieWithGenres.genres)
            binding.textMovieGenres.text = genresStr
            binding.textMovieDate.text =
                getString(R.string.release_date, movieWithGenres.movie.releaseDate)
            binding.textMovieOverview.text = movieWithGenres.movie.overview
            binding.textMovieOriginalLanguage.text =
                getString(R.string.original_language, movieWithGenres.movie.originalLanguage)
            binding.textMovieOriginalTitle.text =
                getString(R.string.original_title, movieWithGenres.movie.originalTitle)

            val favorite = Favorite(
                movieWithGenres.movie.movieId, movieWithGenres.movie.posterPath,
                movieWithGenres.movie.title, EXTRA_MEDIA_TYPE_MOVIE
            )
            val favBtn = binding.ibFavorite
            changeFavoriteIcon(favorite, favBtn)
            favBtn.setOnClickListener {
                favBtnClick(favorite, favBtn)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}