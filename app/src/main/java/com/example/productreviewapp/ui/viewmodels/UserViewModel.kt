package com.example.productreviewapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productreviewapp.data.RetrofitClient
import com.example.productreviewapp.domain.dtos.PhotoProfile
import com.example.productreviewapp.domain.dtos.UserDTO
import com.example.productreviewapp.domain.models.UserProfile
import com.example.productreviewapp.domain.utils.SharedPref
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    val service = RetrofitClient.createAuthService()
    val userId = SharedPref.getUserId()!!
    val auth: String = "Bearer ${SharedPref.getToken()}"
    val imageList = listOf(
        "https://res.cloudinary.com/ddfg5b0z1/image/upload/v1765338923/lain_ildzp4.jpg",
        "https://res.cloudinary.com/ddfg5b0z1/image/upload/v1765339078/joel2_rsyptw.jpg",
        "https://res.cloudinary.com/ddfg5b0z1/image/upload/v1765339224/joel_jdmzeo.webp",
        "https://res.cloudinary.com/ddfg5b0z1/image/upload/v1765339218/espalda_sj1mxt.jpg",
        "https://res.cloudinary.com/ddfg5b0z1/image/upload/v1765339504/fortfazo_iwimm1.jpg",
        "https://res.cloudinary.com/ddfg5b0z1/image/upload/v1765339662/cpaudioorigins_eilrgu.jpg",
        "https://res.cloudinary.com/ddfg5b0z1/image/upload/v1765338988/Gemini_Generated_Image_47wk1r47wk1r47wk_xjw3rs.png",
        "https://res.cloudinary.com/ddfg5b0z1/image/upload/v1765338967/esdee_ctrlo_bk1k3g.jpg",
        "https://res.cloudinary.com/ddfg5b0z1/image/upload/v1765339101/Gemini_Generated_Image_qjhkl8qjhkl8qjhk_tlslem.png",
        "https://res.cloudinary.com/ddfg5b0z1/image/upload/v1765338938/esdee_alfred_yibizw.jpg"
    )

    var isAccountDeleted by mutableStateOf(false)
    var userProfile by mutableStateOf(SharedPref.getUserProfile())
    var name by mutableStateOf(userProfile?.name ?: "")
    var email by mutableStateOf(SharedPref.getUserEmail() ?: "")
    var password by mutableStateOf("")
    var profilePhoto by mutableStateOf(userProfile?.profilePhoto ?: "")

    var loading by mutableStateOf(false)
    var showSheet by mutableStateOf(false)

    fun loadUser(){
        viewModelScope.launch {
            loading = true
            try {
                val result = service.getUser(userId)
                userProfile = UserProfile(userId, result.name, email, result.profilePhoto)
                SharedPref.setUserProfile(userProfile!!)
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
                val result = service.updateUser(
                    auth = auth,
                    id = userId,
                    userDTO = UserDTO(
                        name = name,
                        email = email,
                        password = password.ifEmpty { null },
                    )
                )
                userProfile = result
                SharedPref.setUserEmail(email)
                SharedPref.setUserProfile(result)
            }
            catch (e: Exception){
                Log.e("EditAccountScreen", e.toString())
            }
        }
    }

    fun updateProfilePhoto(){
        viewModelScope.launch {
            loading = true
            try {
               val result = service.updatePhotoProfile(
                    auth = auth,
                    id = userId,
                    photoProfile = PhotoProfile(profilePhoto)
                )
                SharedPref.setUserEmail(result.email)
                SharedPref.setUserProfile(result)

            }catch (e: Exception){
                Log.e("UpdatePhoto", e.toString())
            } finally {
                loading = false
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