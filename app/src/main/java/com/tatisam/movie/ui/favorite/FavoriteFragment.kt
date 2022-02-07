package com.tatisam.movie.ui.favorite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tatisam.movie.MoviesApplication
import com.tatisam.movie.R
import com.tatisam.movie.adapters.FavoriteAdapter
import com.tatisam.movie.databinding.FavoriteFragmentBinding
import com.tatisam.movie.models.Favorite
import com.tatisam.movie.utils.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoriteViewModel =
            ViewModelProvider(this)[FavoriteViewModel::class.java]
        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewFavorites()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @DelicateCoroutinesApi
    private fun viewFavorites() {
        favoriteViewModel.favoriteList.observe(viewLifecycleOwner) { favoriteList ->
            binding.rvFavorites.adapter = FavoriteAdapter(favoriteList) { favorite: Favorite ->
                val args = Bundle()
                val nav = findNavController()
                when (favorite.favType) {
                    EXTRA_MEDIA_TYPE_MOVIE -> {
                        lifecycleScope.launch() {
                            launch(Dispatchers.IO) {
                                MoviesApplication.movieRepository.findMovieWithGenresById(favorite.favId) { result ->
                                    println(result)
                                    launch(Dispatchers.Main) {
                                        args.putParcelable(EXTRA_POPULAR_MOVIE, result)
                                        nav.navigate(R.id.action_navigation_favorite_to_movieFragment, args)
                                    }
                                }
                            }
                        }
                    }
                    EXTRA_MEDIA_TYPE_TV -> {
                        lifecycleScope.launch() {
                            launch(Dispatchers.IO) {
                                MoviesApplication.tvRepository.findTvWithGenresById(favorite.favId) { result ->
                                    println(result)
                                    launch(Dispatchers.Main) {
                                        args.putParcelable(EXTRA_POPULAR_TV, result)
                                        nav.navigate(R.id.action_navigation_favorite_to_tvFragment, args)
                                    }
                                }
                            }
                        }
                    }
                    EXTRA_MEDIA_TYPE_TRENDING -> {
                        lifecycleScope.launch() {
                            launch(Dispatchers.IO) {
                                MoviesApplication.trendingRepository.findTrendingWithGenresById(favorite.favId) { result ->
                                    println(result)
                                    launch(Dispatchers.Main) {
                                        args.putParcelable(EXTRA_TRENDING_NOW, result)
                                        nav.navigate(R.id.action_navigation_favorite_to_trendingNowFragment, args)
                                    }
                                }
                            }
                        }
                    }
                }
                binding.rvFavorites.setOnClickListener {
                    GlobalScope.launch(Dispatchers.IO) {
                        MoviesApplication.favoriteRepository.deleteFavorite(favorite)
                    }
                }
            }
        }
        binding.rvFavorites.layoutManager = LinearLayoutManager(requireContext())
    }

}

