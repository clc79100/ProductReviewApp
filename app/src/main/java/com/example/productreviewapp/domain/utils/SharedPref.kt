package com.example.productreviewapp.domain.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.productreviewapp.domain.models.UserProfile
import com.google.gson.Gson

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
    fun getUserEmail(): String?{
        return preferences.getString("userEmail", null)
    }
    fun setUserEmail(value: String){
        preferences.edit {
            putString("userEmail", value)
        }
    }

    fun setUserProfile(userProfile: UserProfile) {
        val json = Gson().toJson(userProfile)
        preferences.edit {
            putString("userProfile", json)
        }
    }

    fun getUserProfile(): UserProfile? {
        val json = preferences.getString("userProfile", null) ?: return null
        return Gson().fromJson(json, UserProfile::class.java)
    }

    fun clear() {
        preferences.edit {
            clear()
        }
    }
}