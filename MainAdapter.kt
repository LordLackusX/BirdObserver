package com.example.fear5.birdobserver

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.bird_row.view.*

/**
 * Created by fear5 on 4/24/2018.
 */

class MainAdapter : RecyclerView.Adapter<CustomViewHolder>() {

  val listOfBirds = listOf("eagle1","eagle2","eagle3","eagle4")

    override fun getItemCount(): Int {
      // return homeFeed.birds.count()
        return listOfBirds.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.bird_row,parent,false)
        return   CustomViewHolder (cellForRow)

    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
  // val bird = homeFeed.birds .get(position)
      //  holder?.view?.BirdName_Textview?.text = bird.NameEnglish


      val listOfBirds = listOfBirds.get(position)
         holder?.view?.BirdName_Textview?.text = listOfBirds
    }

}

class CustomViewHolder(val view:View) : RecyclerView.ViewHolder(view) {



}