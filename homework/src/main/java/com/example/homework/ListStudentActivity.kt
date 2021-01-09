package com.example.homework

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework.adapter.StudentAdapter
import com.example.homework.service.LoginService
import com.example.homework.service.StudentListService
import kotlinx.android.synthetic.main.activity_list_student2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.net.URL
import java.net.URLConnection
import kotlin.concurrent.thread

class ListStudentActivity : AppCompatActivity() {
    private var studnetList = ArrayList<Student>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_student2)
        initStudent()

    }


    private fun initStudent(){
//        repeat(20){
//            studnetList.add(Student("180804102","才好","1111","女","1111","111111","111111","2132ww32"))
//        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.56.1:9100/homework2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //Log.e("act",retrofit.toString())

        val studentListService = retrofit.create(StudentListService::class.java)

        studentListService.getData().enqueue(object: Callback<List<Student>> {
            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                Toast.makeText(this@ListStudentActivity,"未知错误", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<Student>>,
                response: Response<List<Student>>
            ) {
                this@ListStudentActivity.studnetList = response.body() as ArrayList<Student>
                for(student in this@ListStudentActivity.studnetList){
                    val urlString = "http://192.168.56.1:9100/homework2"+student.photoPath.substring(1)
                    getPic(urlString, student)
                }
                val layoutManager = LinearLayoutManager(this@ListStudentActivity)
                recyclerView.layoutManager = layoutManager
                val adapter = StudentAdapter(studnetList)
                recyclerView.adapter = adapter
            }

        })

    }

    private fun getPic(urlString:String,student:Student){
        thread{
            val url: URL = URL(urlString)
            val connection: URLConnection = url.openConnection() //打开连接

            val stream: InputStream = connection.getInputStream() //获取输输入流

            val bitmap = BitmapFactory.decodeStream(stream)
            //holder.photo.setImageBitmap(bitmap)
            res(student,bitmap)

        }
    }

    private fun res(student:Student,bitmap: Bitmap){
        runOnUiThread{
            student.bitmap = bitmap
        }
    }
}