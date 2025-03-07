package com.example.writepost_cgs

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.*

class EditUserInfoFragment : Fragment() {
    var y: Int = 0
    var m: Int = 0
    var d: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_user_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val parentName: String? = arguments?.getString("name", "")
        var thisActivity = when (parentName) {
            "settings" -> activity as MainActivity
            else -> activity as EditUserInfoActivity
        }

        val editor = MyApplication.sharedPrererenceManager.sharedPreferencesBasicInfo.edit()
        val startDate = view.findViewById<TextView>(R.id.start_date)
        val goalAmount = view.findViewById<EditText>(R.id.goal_amount)

        startDate.text = MyApplication.sharedPrererenceManager.sharedPreferencesBasicInfo.getString("text", "이곳을 눌러 \n시작 날짜를 선택해주세요.")
        goalAmount.setText(MyApplication.sharedPrererenceManager.sharedPreferencesBasicInfo.getInt("goal_amount", 4).toString())

        startDate.apply {
            this.setOnClickListener {
                val cal = Calendar.getInstance()
                DatePickerDialog(thisActivity, DatePickerDialog.OnDateSetListener { datePiker, y, m, d ->
                    this@EditUserInfoFragment.y = y
                    this@EditUserInfoFragment.m = m
                    this@EditUserInfoFragment.d = d

                    var selectM: String = m.toString()
                    var selectD: String = d.toString()
                    if (m < 10) {
                        selectM = "0" + selectM
                    }
                    if (d < 10) {
                        selectD = "0" + selectD
                    }

                    val selectDateString = "$y-$selectM-$selectD"
                    val selectCalender = Calendar.getInstance()
                    selectCalender.set(y, m, d)

                    var day: String = ""
                    when (selectCalender.get(Calendar.DAY_OF_WEEK)) {
                        1 -> day = "일"
                        2 -> day = "월"
                        3 -> day = "화"
                        4 -> day = "수"
                        5 -> day = "목"
                        6 -> day = "금"
                        7 -> day = "토"
                    }
                    this@apply.text = selectDateString + " (" + day + ")"

                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show()
            }
        }

        view.findViewById<TextView>(R.id.complete).setOnClickListener {
            if (startDate.text != "이곳을 눌러 \\n시작 날짜를 선택해주세요." && goalAmount.text.toString() != "") {
                // 정보 저장
                editor.putInt("y", y)
                editor.putInt("m", m)
                editor.putInt("d", d)
                editor.putString("text", startDate.text.toString())
                editor.putInt("goal_amount", goalAmount.text.toString().toInt())
                editor.putInt("cnt", 0)
                editor.commit()

                when (parentName) {
                    "settings" -> Toast.makeText(thisActivity, "수정되었습니다.", Toast.LENGTH_SHORT).show()
                    else -> {
                        val intent = Intent(thisActivity, MainActivity::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                        startActivity(intent)
                        thisActivity.finish()
                    }
                }
            } else Toast.makeText(thisActivity, "위 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}