package com.example.writepost_cgs

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView

class ChangeCntDialog(context: Context) {
    val dlg = Dialog(context)
    val thisContext = context
    lateinit var cnt: EditText
    lateinit var change: TextView
    lateinit var listener: DialogChangeListener
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    fun keyboardUp(){
        cnt.requestFocus()
        imm.showSoftInput(cnt,0)
    }

    fun start() {
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE) // 타이틀바 제거
        dlg.setContentView(R.layout.dialog_change_cnt) // 다이얼로그에 사용할 xml을 불러옴
        dlg.setCancelable(false) // 뒤로가기 버튼 막기
        dlg.setCancelable(true) // 다이얼로그의 바깥 화면을 누르면 다이얼로그 닫히게 만듬

        cnt = dlg.findViewById(R.id.cnt)
        change = dlg.findViewById(R.id.change)

        change.setOnClickListener {
            if (cnt.text.toString() != "") {
                listener.onChagneClicked(true, cnt.text.toString().toInt())
            }
            dlg.dismiss()
        }

        // 다이얼로그 열면 바로 키보드 열기
        dlg.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        dlg.show()
    }

    fun setOnClickedListener(listener: (Boolean, Int) -> Unit) {
        this.listener = object : DialogChangeListener {
            override fun onChagneClicked(chaged: Boolean, cnt: Int) {
                listener(chaged, cnt)
            }
        }
    }

    interface DialogChangeListener {
        fun onChagneClicked(chaged: Boolean, cnt: Int)
    }
}