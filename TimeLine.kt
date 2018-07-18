package com.example.fear5.birdobserver

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.solver.widgets.Snapshot
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.w3c.dom.Text

class TimeLine : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)
        val checkList = findViewById<View>(R.id.CheckBirdListView) as Button
        val observations = findViewById<View>(R.id.BirdObservations) as Button
        val checkjson = findViewById<View>(R.id.JsonCheckButton) as Button
        val DisplayText = findViewById<View>(R.id.DisplayText) as TextView
        val AddBird = findViewById<View>(R.id.AddBirdButton) as Button

   mDatabase = FirebaseDatabase.getInstance().getReference("Users")

        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                val result = snapshot.child( "User").toString()
                DisplayText.text = "Welcome " + result

            }
        } )


    observations.setOnClickListener(){

        val intent = Intent(this,BirdObservations:: class.java)
        startActivityForResult(intent, 21)



    }

        checkList.setOnClickListener(){

            val intent = Intent(this,ListOfBirds:: class.java )
            startActivityForResult(intent,21)

        }

       checkjson.setOnClickListener(){

           val intent = Intent(this,DisplayJson :: class.java)
           startActivityForResult(intent, 21)


       }

        AddBird.setOnClickListener(){

            val intent = Intent(this,Add_Bird_Activity :: class.java)
            startActivityForResult(intent, 21)


        }



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.signOut){
        mAuth.signOut()
        startActivity(Intent(this, MainActivity ::class.java))
        Toast.makeText(this, "Logged Out", Toast.LENGTH_LONG).show()
       }
        return super.onOptionsItemSelected(item)
    }






}
