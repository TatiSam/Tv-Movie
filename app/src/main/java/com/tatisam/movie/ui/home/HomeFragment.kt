package com.tatisam.movie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tatisam.movie.R
import com.tatisam.movie.adapters.MovieAdapter
import com.tatisam.movie.adapters.TrendingNowAdapter
import com.tatisam.movie.adapters.TvAdapter
import com.tatisam.movie.databinding.FragmentHomeBinding
import com.tatisam.movie.models.MoviesWithGenres
import com.tatisam.movie.utils.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick(binding.textPopularMovies)
        onClick(binding.textPopularTv)
        onClick(binding.textTrendingNow)

        homeViewModel.popularMovies.observe(viewLifecycleOwner){ it ->
            binding.rvMovies.adapter = MovieAdapter(it as MutableList<MoviesWithGenres>){
                val args = Bundle()
                args.putParcelable(EXTRA_POPULAR_MOVIE, it)
                val nav = findNavController()
                nav.navigate(R.id.action_navigation_home_to_movieFragment, args)
            }
            binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())
            setHorizontalLayout(binding.rvMovies)
        }

        homeViewModel.popularTvs.observe(viewLifecycleOwner){ it ->
            binding.rvTv.adapter = TvAdapter(it){
                val args = Bundle()
                args.putParcelable(EXTRA_POPULAR_TV, it)
                val nav = findNavController()
                nav.navigate(R.id.action_navigation_home_to_tvFragment, args)
            }
            binding.rvTv.layoutManager = LinearLayoutManager(requireContext())
            setHorizontalLayout(binding.rvTv)
        }

        homeViewModel.trendingNow.observe(viewLifecycleOwner){it ->
            binding.rvTrendingNow.adapter = TrendingNowAdapter(it){
                val args = Bundle()
                args.putParcelable(EXTRA_TRENDING_NOW, it)
                val nav = findNavController()
                nav.navigate(R.id.action_navigation_home_to_trendingNowFragment, args)
            }
            binding.progressHome.visibility = View.GONE
            binding.rvTrendingNow.layoutManager = LinearLayoutManager(requireContext())
            setHorizontalLayout(binding.rvTrendingNow)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClick(view: TextView){
        view.setOnClickListener{
            val args = Bundle()
            args.putString(EXTRA_CATEGORY, view.text.toString())
            val nav = findNavController()
            nav.navigate(R.id.action_navigation_home_to_listFragment, args)
        }
    }

    private fun setHorizontalLayout(rv: RecyclerView){
        rv.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

}