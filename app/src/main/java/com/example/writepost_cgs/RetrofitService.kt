package com.example.writepost_cgs

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

class Result(
    val status: Int,
    val cafeUrl: String,
    val articleId: Int,
    val articleUrl: String
)

interface RetrofitService {
    @Multipart
    @POST("v1/cafe/{clubid}/menu/{menuid}/articles")
    fun uploadPost(
//        @HeaderMap headers: Map<String, String>,
//        @Path("clubid") clubid: Int = 24082687,
//        @Path("menuid") menuid: Int = 454,
//        @Part image: MultipartBody.Part,
//        @Part("subject") subject: RequestBody,
//        @Part("content") content: RequestBody
        @HeaderMap headers: Map<String, String>,
        @Path("clubid") clubid: Int = 24082687,
        @Path("menuid") menuid: Int = 454,
        @Part image: MultipartBody.Part,
        @Part("subject") subject: RequestBody,
        @Part("content") content: RequestBody
    ): Call<Result>

    @GET("v1/nid/me")
    fun getProfile(
        @HeaderMap headers: Map<String, String>
    ): Call<Any>
}