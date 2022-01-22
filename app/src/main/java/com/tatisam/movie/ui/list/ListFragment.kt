package com.tatisam.movie.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tatisam.movie.R
import com.tatisam.movie.adapters.*
import com.tatisam.movie.databinding.FragmentListBinding
import com.tatisam.movie.utils.EXTRA_CATEGORY
import com.tatisam.movie.utils.EXTRA_POPULAR_MOVIE
import com.tatisam.movie.utils.EXTRA_POPULAR_TV
import com.tatisam.movie.utils.EXTRA_TRENDING_NOW

class ListFragment : Fragment() {
    private lateinit var listViewModel: ListViewModel
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listViewModel =
            ViewModelProvider(this)[ListViewModel::class.java]
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        if (args != null) {
            val category = args.getString(EXTRA_CATEGORY)
            binding.textCategoryName.text = category
            when (category) {
                EXTRA_POPULAR_MOVIE -> viewMovies()
                EXTRA_POPULAR_TV -> viewTvs()
                EXTRA_TRENDING_NOW -> viewTrending()
            }
        }
        binding.ibtnFromListToHome.setOnClickListener {
            val nav = findNavController()
            nav.navigate(R.id.action_listFragment_to_navigation_home, args)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun viewMovies(){
        listViewModel.popularMovies.observe(viewLifecycleOwner){ it ->
            binding.rvList.adapter = ListMoviesAdapter(it){
                val args = Bundle()
                args.putParcelable(EXTRA_POPULAR_MOVIE, it)
                val nav = findNavController()
                nav.navigate(R.id.action_listFragment_to_movieFragment, args)
            }
            binding.rvList.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun viewTvs(){
        listViewModel.popularTvs.observe(viewLifecycleOwner) { it ->
            binding.rvList.adapter = ListTvAdapter(it) {
                val args = Bundle()
                args.putParcelable(EXTRA_POPULAR_TV, it)
                val nav = findNavController()
                nav.navigate(R.id.action_listFragment_to_tvFragment, args)
            }
            binding.rvList.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun viewTrending(){
        listViewModel.trendingNow.observe(viewLifecycleOwner){ it ->
            binding.rvList.adapter = ListTrendingNowAdapter(it){
                val args = Bundle()
                args.putParcelable(EXTRA_TRENDING_NOW, it)
                val nav = findNavController()
                nav.navigate(R.id.action_listFragment_to_trendingNowFragment, args)
            }
            binding.rvList.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

}