package com.tatisam.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.tatisam.movie.databinding.ActivityAuthBinding
import com.tatisam.movie.utils.isEmailValid
import android.content.DialogInterface
import android.text.InputType
import android.widget.Toast

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val email get() = binding.content.etEmail.text.toString()
    private val password get() = binding.content.etPassword.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.content.btnLogin.setOnClickListener { login(email, password) }
        binding.content.btnSignIn.setOnClickListener { showSignInDialog() }
    }

    private fun showSignInDialog() {
        val dialog = AlertDialog.Builder(this, R.style.SignInDialogTheme)
        val title = TextView(this)
        title.text = getString(R.string.sign_in)
        title.setPadding(48, 48, 48, 48)
        title.textSize = 20F
        title.setTextColor(getColor(R.color.white))
        dialog.setCustomTitle(title)

        val layout = LinearLayout(this);
        layout.orientation = LinearLayout.VERTICAL;
        val layoutParamsEditText = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParamsEditText.setMargins(30, 40, 40, 0)
        val layoutParamsText = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParamsText.setMargins(60, 0, 0, 0)

        val editTextEmail = EditText(this)
        editTextEmail.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        editTextEmail.hint = getString(R.string.email)
        addStyleToEditText(editTextEmail)
        layout.addView(editTextEmail, layoutParamsEditText)

        val editTextPassword = EditText(this)
        editTextPassword.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        editTextPassword.hint = getString(R.string.password)
        addStyleToEditText(editTextPassword)
        layout.addView(editTextPassword, layoutParamsEditText)

        val text = TextView(this)
        text.text = getString(R.string.password_mess)
        text.textSize = 10F
        text.setTextColor(getColor(R.color.light_green))
        layout.addView(text, layoutParamsText)

        dialog.setView(layout)
        dialog.setPositiveButton(getString(R.string.sign_in),
            DialogInterface.OnClickListener { _, _ ->
                val email = editTextEmail.text.toString()
                val password = editTextPassword.text.toString()
                register(email, password)
            })
        dialog.show()
    }

    private fun addStyleToEditText(editText: EditText) {
        editText.setPadding(64, 24, 64, 24)
        editText.textSize = 20f
        editText.setHintTextColor(getColor(R.color.light_green))
        editText.setTextColor(getColor(R.color.light_green))
        editText.setBackgroundResource(R.drawable.edit_text_border)
    }

    private fun showMessage(view: View, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showProgress() {
        binding.content.progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.content.progress.visibility = View.INVISIBLE
    }

    private fun hideKeyBoard() {
        val imm = getSystemService(InputMethodManager::class.java)
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    private fun checkEmail(email: String): Boolean = email.isEmailValid()
    private fun checkPassword(password: String): Boolean = password.length >= 6

    private fun login(email: String, password: String) {
        hideKeyBoard()
        if (!checkEmail(email) || !checkPassword(password)) {
            showMessage(binding.content.btnLogin, getString(R.string.not_valid))
            return
        }
        showProgress()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                hideProgress()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                hideProgress()
                showMessage(
                    binding.content.btnLogin,
                    it.localizedMessage.toString() ?: "Unknown error"
                )
            }
    }

    private fun register(email: String, password: String) {
        if (!checkEmail(email) || !checkPassword(password)) {
            showMessage(binding.content.btnSignIn, getString(R.string.not_valid))
            return
        }
        showProgress()
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                hideProgress()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                hideProgress()
                showMessage(
                    binding.content.btnSignIn,
                    it.localizedMessage.toString() ?: "Unknown error"
                )
            }
    }

}