package com.example.homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.homework.service.LoginService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login.setOnClickListener {
            //val intent = Intent(this,ListStudentActivity::class.java)
           // startActivity(intent)
            val username = username.text.toString()
            val password = password.text.toString()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.56.1:9100/homework2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                //Log.e("act",retrofit.toString())

            val loginService = retrofit.create(LoginService::class.java)

            loginService.getData(username, password).enqueue(object:Callback<Map<String,String>>{
                override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                    Toast.makeText(this@LoginActivity,"未知错误",Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<Map<String, String>>,
                    response: Response<Map<String, String>>
                ) {
                    val map = response.body()
                    if(map?.get("result").equals("ok")){
                        val intent = Intent(this@LoginActivity,ListStudentActivity::class.java)
                         startActivity(intent)
                    }else{
                        Toast.makeText(this@LoginActivity,"用户名或密码错误",Toast.LENGTH_LONG).show()
                    }
                }

            })

        }
        register.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
