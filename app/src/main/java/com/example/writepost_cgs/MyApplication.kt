package com.example.writepost_cgs

import android.app.Application
import android.content.Context

class MyApplication : Application() {
    companion object {
        lateinit var sharedPrererenceManager: SharedPrererenceManager
    }

    override fun onCreate() {
        sharedPrererenceManager = SharedPrererenceManager(applicationContext)
        super.onCreate()
    }
}

class SharedPrererenceManager(context: Context) {
    val sharedPreferencesUserInfo = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
    val sharedPreferencesBasicInfo = context.getSharedPreferences("basic_info", Context.MODE_PRIVATE)
}