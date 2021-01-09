package com.example.homework.adapter

import android.R.attr.bitmap
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.ListStudentActivity
import com.example.homework.R
import com.example.homework.Student
import java.io.InputStream
import java.net.URL
import java.net.URLConnection

import kotlin.concurrent.thread


class StudentAdapter(val studentList:List<Student>):
    RecyclerView.Adapter<StudentAdapter.ViewHolder>(){
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val stuNo:TextView = view.findViewById(R.id.stuNo)
        val stuName:TextView = view.findViewById(R.id.stuName)
        val photo:ImageView = view.findViewById(R.id.photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = studentList[position]
        holder.stuNo.text = student.stuNo
        holder.stuName.text = student.stuName
       holder.photo.setImageBitmap(student.bitmap)


    }







}