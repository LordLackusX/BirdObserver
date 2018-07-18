package com.example.fear5.birdobserver

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_bird_observations.*
import kotlinx.android.synthetic.main.bird_row.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class BirdObservations : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bird_observations)

        BirdList.layoutManager = LinearLayoutManager(this)
         BirdList.adapter = MainAdapter()


        fetchjson()
    }


    fun fetchjson(){
        println("attempting to Fetch JSON ")
       val url = " http://birdobservationservice.azurewebsites.net/Service1.svc/observations"

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback{


            override fun onResponse(call: Call?, response: okhttp3.Response?) {
                val body = response?.body()?.string()
                println(body)

                  val gson  = GsonBuilder().create()

              //   val homeFeed  =   gson.fromJson(body,HomeFeed :: class.java )

                //BirdList.adapter = MainAdapter(homeFeed)

            }


            override fun onFailure(call: Call?, e: IOException?) {
               println("failed to execute ")
           }


        })

    }


    class HomeFeed (val birds : List<bird> )
    class bird (val BirdId: Int, val Latitude : Int, val Longitude : Int , val NameEnglish : String)





}
