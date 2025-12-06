package com.example.productreviewapp

import android.app.Application
import com.example.productreviewapp.domain.utils.SharedPref

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }
}
