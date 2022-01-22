package com.tatisam.movie.ui.settings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.tatisam.movie.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {
    private lateinit var settingsViewModel: SettingsViewModel
    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProvider(this)[SettingsViewModel::class.java]

        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.tvLogOut.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}