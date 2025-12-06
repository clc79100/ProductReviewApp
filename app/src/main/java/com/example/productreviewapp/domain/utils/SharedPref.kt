package com.example.productreviewapp.domain.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object SharedPref {
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    fun getToken(): String? {
        return preferences.getString("token", null)
    }

    fun setToken(value: String) {
        preferences.edit {
            putString("token", value)
        }
    }


    fun getIsLogged(): Boolean {
        return preferences.getBoolean("isLogged", false)
    }

    fun setIsLogged(value: Boolean) {
        preferences.edit {
            putBoolean("isLogged", value)
        }
    }


    fun getUserId(): String?{
        return preferences.getString("userId", null)
    }

    fun setUserId(value: String){
        preferences.edit{
            putString("userId", value)
        }
    }
}