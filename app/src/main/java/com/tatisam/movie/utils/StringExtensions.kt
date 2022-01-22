package com.tatisam.movie.utils

fun CharSequence.isEmailValid(): Boolean{
    return this.length >= 6 && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}