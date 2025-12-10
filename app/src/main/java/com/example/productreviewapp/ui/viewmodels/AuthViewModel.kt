package com.example.productreviewapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productreviewapp.data.RetrofitClient
import com.example.productreviewapp.domain.dtos.Login
import com.example.productreviewapp.domain.dtos.UserDTO
import com.example.productreviewapp.domain.utils.SharedPref
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel(){
    val service = RetrofitClient.createAuthService()
    var isLogged by mutableStateOf(SharedPref.getIsLogged())
    var showAlertDialog by mutableStateOf(false)
    var isEnabled by mutableStateOf(true)

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    var loading by mutableStateOf(false)

    fun login(){
        viewModelScope.launch{
            loading = true
            try {
                isEnabled = false

                val login = Login(
                    email = email,
                    password = password
                )
                val result = service.login(login)
                SharedPref.setToken(result.token)
                SharedPref.setUserId(result.user.id)
                SharedPref.setUserEmail(result.user.email)
                SharedPref.setIsLogged(true)
                isLogged = true
            }catch (e: Exception){
                Log.e("LoginScreen", e.toString())
                showAlertDialog = true
            } finally {
                isEnabled = true
                loading = false
            }
        }
    }

    fun register(){
        viewModelScope.launch {
            loading = true
            try {
                isEnabled = false
                val user = UserDTO(
                    name = name,
                    email = email,
                    password = password,
                )
                service.register(user)
            }catch (e: Exception){
                Log.e("RegisterScreen", e.toString())
            }
            finally {
                loading = false
                isEnabled = true
            }
        }
    }
}