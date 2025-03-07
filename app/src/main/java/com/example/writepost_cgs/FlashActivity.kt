package com.example.writepost_cgs

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class FlashActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash)

        // 새로운 주차 확인
        val recodedDay = Calendar.getInstance().apply {
            set(Calendar.YEAR, MyApplication.sharedPrererenceManager.sharedPreferencesBasicInfo.getInt("y", 2000))
            set(Calendar.MONTH, MyApplication.sharedPrererenceManager.sharedPreferencesBasicInfo.getInt("m", 4))
            set(Calendar.DAY_OF_MONTH, MyApplication.sharedPrererenceManager.sharedPreferencesBasicInfo.getInt("d", 7))
        }.timeInMillis
        val nowDay = Calendar.getInstance().timeInMillis
        val fewDay = getIgnoredTimeDays(nowDay) - getIgnoredTimeDays(recodedDay)

        if (fewDay / (24 * 60 * 60 * 1000) > 6) {
            val editor = MyApplication.sharedPrererenceManager.sharedPreferencesBasicInfo.edit()
            editor.putInt("y", Calendar.getInstance().get(Calendar.YEAR))
            editor.putInt("m", Calendar.getInstance().get(Calendar.MONTH))
            editor.putInt("d", Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
            editor.putInt("cnt", 0)
            editor.commit()
        }


        // 이동 분기처리
        if (MyApplication.sharedPrererenceManager.sharedPreferencesUserInfo.getString("token", "") == "") {
            startActivity(Intent(this@FlashActivity, LoginActivity::class.java))
        } else {
            if (MyApplication.sharedPrererenceManager.sharedPreferencesBasicInfo.getInt("goal_amount", -1) == -1) {
                startActivity(Intent(this@FlashActivity, EditUserInfoActivity::class.java))
            } else {
                startActivity(Intent(this@FlashActivity, MainActivity::class.java))
            }
        }
    }
}

// 시간, 분, 초, 밀리초 제외 시키기
fun getIgnoredTimeDays(time: Long): Long {
    return Calendar.getInstance().apply {
        timeInMillis = time

        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis
}