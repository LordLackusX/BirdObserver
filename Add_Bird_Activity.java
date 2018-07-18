package com.example.fear5.birdobserver;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Add_Bird_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__bird_);
        }





    public void addBird(View view) {
        String name = ((EditText) findViewById(R.id.Add_Mame)).getText().toString();
        String comment = ((EditText) findViewById(R.id.AddComment)).getText().toString();
        String position = ((EditText) findViewById(R.id.AddPosition)).getText().toString();
        String timeToString = ((EditText) findViewById(R.id.AddTime)).getText().toString();
        double time = Double.parseDouble(timeToString);
        //String jsonDocument =
        //        "{\"Title\":\"" + title + "\", \"Author\":\"" + author + "\", \"Publisher\":\"" + publisher + "\", \"Price\":" + price + "}";

        TextView messageView = findViewById(R.id.Bird_Message);
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("NameEnglish", name);
            jsonObject.put("Comment", comment);
            jsonObject.put("Placename", position);
         //   jsonObject.put("Price", timeToString);


            String jsonDocument = jsonObject.toString();
            messageView.setText(jsonDocument);
            PostBirdTask task = new PostBirdTask();
            task.execute("http://birdobservationservice.azurewebsites.net/Service1.svc/observations", jsonDocument);
        } catch (JSONException ex) {
            messageView.setText(ex.getMessage());
        }

    }



    private class PostBirdTask extends AsyncTask<String, Void, CharSequence> {
        //private final String JsonDocument;

        //PostBookTask(String JsonDocument) {
        //    this.JsonDocument = JsonDocument;
        //}

        @Override
        protected CharSequence doInBackground(String... params) {
            String urlString = params[0];
            String jsonDocument = params[1];
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
                osw.write(jsonDocument);
                osw.flush();
                osw.close();
                int responseCode = connection.getResponseCode();
                if (responseCode / 100 != 2) {
                    String responseMessage = connection.getResponseMessage();
                    throw new IOException("HTTP response code: " + responseCode + " " + responseMessage);
                }
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line = reader.readLine();
                return line;
            } catch (MalformedURLException ex) {
                cancel(true);
                String message = ex.getMessage() + " " + urlString;
                Log.e("BOOK", message);
                return message;
            } catch (IOException ex) {
                cancel(true);
                Log.e("BOOK", ex.getMessage());
                return ex.getMessage();
            }
        }

        @Override
        protected void onPostExecute(CharSequence charSequence) {
            super.onPostExecute(charSequence);
            TextView messageView = findViewById(R.id.Bird_Message);
            messageView.setText(charSequence);
            Log.d("MINE", charSequence.toString());
            finish();
        }

        @Override
        protected void onCancelled(CharSequence charSequence) {
            super.onCancelled(charSequence);
            TextView messageView = findViewById(R.id.Bird_Message);
            messageView.setText(charSequence);
            Log.d("MINE", charSequence.toString());
            finish();
        }
    }


}
