package com.example.homework.service

import com.example.homework.Student
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface StudentListService {
    @POST("listStudents")
    fun getData() : Call<List<Student>>
}