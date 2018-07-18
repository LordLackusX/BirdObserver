package com.example.fear5.birdobserver

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_bird_observations.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {

    val  mAuth = FirebaseAuth.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val LogIn = findViewById<View>(R.id.LogIn) as Button

        LogIn.setOnClickListener(View.OnClickListener { view -> login()  })








        Register.setOnClickListener() {

            val intent = Intent(this,LoginActivity::class.java)
            startActivityForResult(intent, 21)


        }








    }

    private fun login(){

        var EmailField = findViewById<View>(R.id.EmailField) as EditText
        var PasswordField = findViewById<View>(R.id.PasswordField) as EditText

        var email = EmailField.text.toString()
        var password = PasswordField.text.toString()

        if (!email.isEmpty()  && !password.isEmpty()){
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful)
                { startActivity(Intent(this, TimeLine :: class.java))
                    Toast.makeText(this , "login succesfull" , Toast.LENGTH_LONG).show()

                } else {


                }



            })
        } else {
            Toast.makeText(this,"this field must not be empty. ", Toast.LENGTH_LONG ).show()



        }
    }





}
