package com.example.productreviewapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productreviewapp.data.RetrofitClient
import com.example.productreviewapp.domain.dtos.UserDTO
import com.example.productreviewapp.domain.models.UserProfile
import com.example.productreviewapp.domain.utils.SharedPref
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    val service = RetrofitClient.createAuthService()
    val userId = SharedPref.getUserId()!!
    val auth: String = "Bearer ${SharedPref.getToken()}"
    var isLogged by mutableStateOf(SharedPref.getIsLogged())
    var isAccountDeleted by mutableStateOf(false)
    var userProfile by mutableStateOf<UserProfile?>(SharedPref.getUserProfile())
    var loading by mutableStateOf(false)
    var name by mutableStateOf(userProfile?.name ?: "")
    var email by mutableStateOf(userProfile?.email ?: "")
    var password by mutableStateOf("")
    var profilePhoto by mutableStateOf(userProfile?.profilePhoto ?: "")

    fun loadUser(){
        viewModelScope.launch {
            loading = true
            try {
                userProfile = service.getUser(userId)
                SharedPref.setUserProfile(userProfile ?: UserProfile(id = userId,name = "", email = "", profilePhoto = ""))
            }
            catch (e: Exception){
                Log.e("AccountScreen: loading user", e.toString())
            }
            finally {
                loading = false
            }
        }
    }

    fun updateUser(){
        viewModelScope.launch {
            loading = true
            try {
                service.updateUser(
                    auth = auth,
                    id = userId,
                    userDTO = UserDTO(
                        name = name,
                        email = email,
                        password = password,
                        profilePhoto = profilePhoto
                    )
                )
            }
            catch (e: Exception){
                Log.e("EditAccountScreen", e.toString())
            }
        }
    }

    fun deleteAccount(){
        viewModelScope.launch {
            loading = true
            try {
                service.deleteUser(
                    auth = auth,
                    id = userId
                )
                SharedPref.clear()
                isAccountDeleted = true
            }catch (e: Exception){
                Log.e("AccountScreen", e.toString())
            }
        }
    }
}