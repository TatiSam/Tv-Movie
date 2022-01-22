package com.tatisam.movie

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tatisam.movie.databinding.ActivityMainBinding
import com.tatisam.movie.models.AppUser

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance().reference

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favorite, R.id.navigation_settings
            )
        )
        navView.setupWithNavController(navController)
    }

    /*override fun onResume() {
        super.onResume()
        FirebaseAuth.getInstance().addAuthStateListener {
            val user = FirebaseAuth.getInstance().currentUser
            if(user != null){
                database.child("users").child(user.uid).setValue(AppUser(user.uid, user.email))
            }else{
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }*/
}