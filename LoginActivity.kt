package com.example.fear5.birdobserver

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

  val mAuth  = FirebaseAuth.getInstance()
  lateinit var mDatabase : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val RegBtn = findViewById<View>(R.id.RegBtn) as Button

        mDatabase = FirebaseDatabase.getInstance().getReference("Users")

        RegBtn.setOnClickListener(View.OnClickListener { view -> register()  })


    }
    private fun register() {

        val EmailField = findViewById<View>(R.id.EmailField) as EditText
        val UserField = findViewById<View>(R.id.UserField) as EditText
        val PasswordField = findViewById<View>(R.id.PasswordField) as EditText

        var email = EmailField.text.toString()
        var user = UserField.text.toString()
        var password = PasswordField.text.toString()

        if (!user.isEmpty() && !email.isEmpty() && !password.isEmpty()){
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, OnCompleteListener { task ->

                if (task.isSuccessful) {

                val user = mAuth.currentUser
                val uid = user!!.uid
                mDatabase.child(uid).child("user").setValue(user)
                Toast.makeText(this,"You are now signed in ! ", Toast.LENGTH_LONG).show()

                } else {

                    Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
                }



            })
        }else{
        Toast.makeText(this, "Field must not be empty ! " , Toast.LENGTH_LONG).show()



        }
    }

}
