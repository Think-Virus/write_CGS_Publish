package com.example.writepost_cgs

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.airbnb.lottie.L
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class EditUserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user_info)

        val fragmentManger = supportFragmentManager
        val editUserInfoFragment = EditUserInfoFragment()
        val bundle = Bundle()
        bundle.putString("name", "firstInput")
        val transaction = fragmentManger.beginTransaction()
        editUserInfoFragment.arguments = bundle
        transaction.replace(R.id.fragment_container, editUserInfoFragment)
        transaction.commit()
    }
}