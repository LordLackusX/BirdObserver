package com.example.fear5.birdobserver;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BirdActivity extends AppCompatActivity {

    private Obsercation obsercation;
    private EditText birdView, idView, commentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird);


        Intent intent = getIntent();
        obsercation = (Obsercation) intent.getSerializableExtra("BIRD");


        birdView = findViewById(R.id.EnglishName);
        birdView.setText(obsercation.getId());

        idView = findViewById(R.id.birdID);
        idView.setText(obsercation.getId());

        commentView = findViewById(R.id.COMMENT);
        commentView.setText(obsercation.getComment());


    }


    public void deleteBook(View view) {
        DeleteTask task = new DeleteTask();
        task.execute("http://birdobservationservice.azurewebsites.net/Service1.svc/observations");
        finish();
    }

  //  public void updateBook(View view) {
        // code missing: Left as an exercise
    //}


    public void back(View view) {
        finish();
    }

    private class DeleteTask extends AsyncTask<String, Void, CharSequence> {
        @Override
        protected CharSequence doInBackground(String... urls) {
            String urlString = urls[0];
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("DELETE");
                int responseCode = connection.getResponseCode();
                if (responseCode % 100 != 2) {
                    throw new IOException("Response code: " + responseCode);
                }
                return "Nothing";
            } catch (MalformedURLException e) {
                return e.getMessage() + " " + urlString;
            } catch (IOException e) {
                return e.getMessage();
            }
        }


        @Override
        protected void onCancelled(CharSequence charSequence) {
            super.onCancelled(charSequence);
            TextView messageView = findViewById(R.id.book_message_textview);
            messageView.setText(charSequence);
        }


    }
}

