package com.example.writepost_cgs

import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.bumptech.glide.Glide
import com.navercorp.nid.NaverIdLoginSDK
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.net.URLEncoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WriteFragment() : Fragment() {
    var imageUri: Uri? = null

    lateinit var postImageView: ImageView
    lateinit var upload: TextView
    lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    lateinit var title: TextView
    lateinit var content: EditText
    lateinit var screenUpload: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_write, container, false)
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postImageView = view.findViewById(R.id.imageView)
        upload = view.findViewById(R.id.upload)
        title = view.findViewById(R.id.title)
        content = view.findViewById(R.id.content)
        screenUpload = view.findViewById(R.id.screen_upload)
        view.findViewById<TextView>(R.id.change_cnt).setOnClickListener {
            val dlg = ChangeCntDialog(activity as MainActivity)
            dlg.setOnClickedListener { changed, cnt ->
                if (changed) {
                    MyApplication.sharedPrererenceManager.sharedPreferencesBasicInfo.edit().putInt("cnt", cnt).commit()
                    makeTitle()
                }
            }
            dlg.start()
            dlg.keyboardUp()
        }

        /** Naver Login Module Initialize */
        val naverClientId = BuildConfig.NAVER_CLIENT_ID
        val naverClientSecret = BuildConfig.NAVER_CLIENT_SECRET
        val naverClientName = BuildConfig.NAVER_CLIENT_NAME
        NaverIdLoginSDK.initialize((activity as MainActivity), naverClientId, naverClientSecret, naverClientName)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        val glide = Glide.with(this)
        imagePickerLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                imageUri = it.data?.data
                if (imageUri == null) {
                    return@registerForActivityResult
                }
                screenUpload.visibility = View.INVISIBLE
                glide.load(imageUri).into(postImageView)
            }

        postImageView.apply {
            this.setOnClickListener {
                makePost()
            }
        }

        screenUpload.setOnClickListener {
            Toast.makeText(activity as MainActivity, "먼저 사진을 등록해주세요.", Toast.LENGTH_SHORT).show()
        }

        makeTitle()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun makeTitle() {
        val nowDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val nowDateFormated = nowDate.format(formatter)
        val goalAmount = MyApplication.sharedPrererenceManager.sharedPreferencesBasicInfo.getInt("goal_amount", 4)
        val cnt = MyApplication.sharedPrererenceManager.sharedPreferencesBasicInfo.getInt("cnt", 0) + 1

        title.text = nowDateFormated + "($cnt/$goalAmount)" // 제목 설정
    }

    private fun getRealFile(uri: Uri): File? {
        var uri: Uri? = uri
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        if (uri == null) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
        var cursor: Cursor? = (activity as MainActivity).getContentResolver().query(
            uri!!,
            projection,
            null,
            null,
            MediaStore.Images.Media.DATE_MODIFIED + " desc"
        )
        if (cursor == null || cursor.getColumnCount() < 1) {
            return null
        }
        val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val path: String = cursor.getString(column_index)
        if (cursor != null) {
            cursor.close()
            cursor = null
        }
        return File(path)
    }


    fun makePost() {
        imagePickerLauncher.launch(
            Intent(Intent.ACTION_PICK).apply {
                this.type = MediaStore.Images.Media.CONTENT_TYPE
            })

        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        //        view.findViewById<TextView>(R.id.upload).setOnClickListener {
        upload.setOnClickListener {
            val file = getRealFile(imageUri!!) // 실제 파일을 가져옴

            //            val requestFile = RequestBody.create(
            //                MediaType.parse(
            //                    this.contentResolver.getType(imageUri!!) // 올릴 파일의 타입을 알려주는 부분
            //                ), file // 실제 파일
            //            )
            val requestFile = file?.asRequestBody("image/*".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData(
                "image",
                file!!.name,
                requestFile!!
            )
            val header = HashMap<String, String>()
            header.put("Authorization", "Bearer " + NaverIdLoginSDK.getAccessToken())
            val titleText = title.text.toString()
            val contentText = content.text.toString() + " "
            val titleBody =
                RequestBody.create(MultipartBody.FORM, URLEncoder.encode(titleText, "UTF-8"))
            val contentBody =
                RequestBody.create(MultipartBody.FORM, URLEncoder.encode(contentText, "UTF-8"))
            //이미지 넣어야 함

            retrofitService.uploadPost(
                header,
                24082687,
                454,
                body,
                titleBody,
                contentBody
            ).enqueue(object : Callback<Result> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    Log.d("cafee", response.message())
                    if (response.isSuccessful) {
                        Log.d("cafee", response.message())

                        // 현재 횟수 증가
                        MyApplication.sharedPrererenceManager.sharedPreferencesBasicInfo.edit().putInt("cnt", MyApplication.sharedPrererenceManager.sharedPreferencesBasicInfo.getInt("cnt", 0) + 1)
                            .commit()

                        //History에 저장
                        val db = Room.databaseBuilder(
                            activity as MainActivity,
                            AppDatabases::class.java,
                            "post_history_db"
                        ).allowMainThreadQueries().build()
                        val bitmapImage: Bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            ImageDecoder.decodeBitmap(ImageDecoder.createSource((activity as MainActivity).getContentResolver(), imageUri!!))
                        } else {
                            MediaStore.Images.Media.getBitmap((activity as MainActivity).getContentResolver(), imageUri)
                        }
                        val post = PostHistory(title = titleText, content = contentText, image = Converter().toByteArray(bitmapImage))
                        db.PostHistoryDao().insert(post)

                        PostAmount.addPost()
                        Toast.makeText(activity as MainActivity, "등록되었습니다.", Toast.LENGTH_SHORT).show()

                        //초기화
                        makeTitle()
                        content.setText(null)
                        screenUpload.visibility = View.VISIBLE
                        postImageView.setImageResource(R.drawable.add)

                        //history 화면으로 이동
                        (activity as MainActivity).moveHistoryTab()
                    } else if(response.code() == 401) { //반응은 왔으나 업로드 실패한 경우 확인
                        Toast.makeText(activity as MainActivity, "권한 오류가 발생하였습니다.\n로그아웃 후에 다시 진행해주시길 바랍니다.",Toast.LENGTH_SHORT).show()

                        NaverIdLoginSDK.logout()
                        startActivity(Intent(activity as MainActivity,LoginActivity::class.java))
                        (activity as MainActivity).finish()
                    } else{ //아예 다른 문제
                        Toast.makeText(activity as MainActivity, "업로드 불가능\n오류 코드 : ${response.code()}\n오류 메시지 : ${response.message()}",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Result>, t: Throwable) {
                    Log.d("cafee", "" + call.toString() + " / " + t.message)
                    Log.d("cafee", "failed")
                }
            })
        }
    }
}