package com.example.homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homework.service.LoginService
import com.example.homework.service.RegisterService
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register.setOnClickListener {
            //val intent = Intent(this,LoginActivity::class.java)
            //startActivity(intent)
            val username = username.text.toString()
            val password = password.text.toString()
            val passwordAgain = passwordAgain.text.toString()
            val stuno = stuno.text.toString()

            if(!password.equals(passwordAgain)){
                Toast.makeText(this@RegisterActivity,"两次密码不同", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.56.1:9100/homework2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            //Log.e("act",retrofit.toString())

            val registerService = retrofit.create(RegisterService::class.java)

            registerService.getData(username, password,stuno).enqueue(object: Callback<Map<String, String>> {
                override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity,"未知错误", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<Map<String, String>>,
                    response: Response<Map<String, String>>
                ) {
                    val map = response.body()
                    if(map?.get("result").equals("ok")){
                        val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this@RegisterActivity,"保存失败！", Toast.LENGTH_LONG).show()
                    }
                }

            })

        }
    }
}