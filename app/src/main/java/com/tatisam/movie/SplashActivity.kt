package com.tatisam.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tatisam.movie.models.AppUser

class SplashActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        database = FirebaseDatabase.getInstance().reference

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            startApp()
        }, 4000)
    }

    private fun startApp() {
        FirebaseAuth.getInstance().addAuthStateListener {
            val user = FirebaseAuth.getInstance().currentUser
            if(user != null){
                database.child("users").child(user.uid).setValue(AppUser(user.uid, user.email))
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

}