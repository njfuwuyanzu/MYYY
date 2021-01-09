package com.example.homework.service

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface RegisterService {
    @POST("registerUser")
    fun getData(@Query("username") username:String, @Query("password") password:String,@Query("stuno") stuno:String) : Call<Map<String, String>>
}