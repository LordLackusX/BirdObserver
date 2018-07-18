package com.example.fear5.birdobserver;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Obsercation implements Serializable {
    @SerializedName("BirdID")
    private int birdID;
    @SerializedName("Comment")
    private String Comment;
    @SerializedName("NameEnglish")
    private String NameEnglish;
    @SerializedName("NameDanish")
    private String NameDanish;
    @SerializedName("Longitude")
    private double Longitude;
    @SerializedName("Latitude")
    private double Latitude;
    @SerializedName("UserID")
    private String UserID;
    @SerializedName("Placename")
    private String Placename;
    @SerializedName("Population")
    private int Population;


    public Obsercation() {
    }

    public Obsercation(int birdID, String comment, String nameEnglish, String nameDanish, double longitude, double latitude, String userID, String placement, int population ) {
        this.birdID = birdID;
        this.Comment = comment;
        this.NameEnglish  = nameEnglish;
        this.NameDanish = nameDanish;
        this.Longitude = longitude;
        this.Latitude = latitude;
        this.UserID = userID;
        this.Placename = placement;
        this.Population = population;

    }

    public void setId(int birdID) {
        this.birdID = birdID ;
    }

    public void setComment(String comment) {
        this.Comment = comment;
    }

    public void setNameEnglish(String nameEnglish) {
        this.NameEnglish = nameEnglish;
    }

    public void setNameDanish(String nameDanish) {
        this.NameDanish = nameDanish;
    }

    public void setLatitude(double latitude) {
        this.Latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.Longitude = longitude;
    }

    public void setUserID ( String userID){

        this.UserID = userID;
    }

    public void setPlacename (String placename){

        this.Placename = placename;
    }

    public  void setPopulation (int population){

        this.Population = population;

    }

    public int getId() {
        return birdID;
    }

    public String getComment() {
        return Comment;
    }

    public String getNameEnglish() {
        return NameEnglish;
    }

    public String getNameDanish() {
        return NameDanish;
    }

    public double getLatitude() {
        return Latitude;
    }


    public double getLongitude() {
        return  Longitude;
    }

    public String getUserID(){

        return  UserID;
    }

    public String getPlacename(){

        return Placename;
    }

    public int getPopulation(){

        return Population;
    }



    @Override
    public String toString(){

        return NameEnglish;

    }
}
