package com.example.fear5.birdobserver;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DisplayJson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_json);
     final ReadJsonFeedTask task = new ReadJsonFeedTask();
     task.execute("http://birdobservationservice.azurewebsites.net/Service1.svc/observations");

    }

private class ReadJsonFeedTask extends AsyncTask<String,Void,String>

{

@Override
    protected String doInBackground(String...urls){
    try {
    return readJSonFeed(urls[0]);
    } catch (IOException e) {
        Log.e("HELLO", e.toString());
        cancel(true);
        return e.toString();


    }


}


@Override
    protected void onPostExecute(String result){

    final TextView textview = findViewById(R.id.MainResultTextView);
    try {
        JSONObject jsonObject = new JSONObject(result);
        final String ID = jsonObject.getString("BirdId");
        final String NameEnglish = jsonObject.getString("NameEnglish");
        textview.append(ID + ":" + NameEnglish);




    }

    catch (JSONException e) {
        textview.append(e.toString());
    }


}

@Override

protected void onCancelled(String message){
super.onCancelled(message);
final TextView textView = findViewById(R.id.MainResultTextView);
textView.setText(message);

}





}





    private String readJSonFeed(String urlString) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        final InputStream content = openHttpConnection(urlString);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        while (true) {
            final String line = reader.readLine();
            if (line == null)
                break;
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }



    private InputStream openHttpConnection(final String urlString)
            throws IOException {
        final URL url = new URL(urlString);
        final URLConnection conn = url.openConnection();
        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        final HttpURLConnection httpConn = (HttpURLConnection) conn;
        httpConn.setAllowUserInteraction(false);
        // No user interaction like dialog boxes, etc.
        httpConn.setInstanceFollowRedirects(true);
        // follow redirects, response code 3xx
        httpConn.setRequestMethod("GET");
        httpConn.connect();
        final int response = httpConn.getResponseCode();
        if (response == HttpURLConnection.HTTP_OK) {
            return httpConn.getInputStream();
        } else {
            throw new IOException("HTTP response not OK");
        }
    }






}
