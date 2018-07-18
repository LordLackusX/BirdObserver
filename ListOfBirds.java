package com.example.fear5.birdobserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ListOfBirds extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_birds);

        TextView listHeader = new TextView(this);
        listHeader.setText("Our Beautiful Birds");
        listHeader.setTextAppearance(this, android.R.style.TextAppearance_Large);
        ListView listView = (ListView)findViewById(R.id.brdview);
        listView.addHeaderView(listHeader);



    }

    @Override
    protected void onStart() {
        super.onStart();
        ReadTask task = new ReadTask();
        task.execute("http://birdobservationservice.azurewebsites.net/Service1.svc/observations");
    }






    private class ReadTask extends ReadHttpTask {
        @Override
        protected void onPostExecute(CharSequence jsonString) {

            /*
            final List<Book> books = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(jsonString.toString());
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    String author = obj.getString("Author");
                    double price = obj.getDouble("Price");
                    String title = obj.getString("Title");
                    String publisher = obj.getString("Publisher");
                    int id = obj.getInt("Id");
                    Book book = new Book(id, author, title, publisher, price);
                    books.add(book);
                }
                */
            Log.d("CLICK","onSTART");
            Gson gson = new GsonBuilder().create();
            final Obsercation[] obsercations = gson.fromJson(jsonString.toString(), Obsercation[].class);

            ListView listView = findViewById(R.id.brdview);
            ArrayAdapter<Obsercation> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, obsercations);
          // BirdListAdapter adapter = new BirdListAdapter(getBaseContext(), R.layout.activity_list_of_birds, obsercations);
            listView.setAdapter(adapter);
            Log.d("CLICK", " onCLICKED");
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("CLICK", "onItemClick");
                    Intent intent = new Intent(getBaseContext(), BirdActivity.class);
                    //Book book = books.get((int) id);
                    //Obsercation bird  = obsercations[(int) id];
                    intent.putExtra("BIRD", obsercations);
                    startActivity(intent);
               }
            });
           /* } catch (JSONException ex) {
                messageTextView.setText(ex.getMessage());
                Log.e("BOOKS", ex.getMessage());
            }*/
        }

        @Override
        protected void onCancelled(CharSequence message) {
            TextView messageTextView = findViewById(R.id.DisplayComment);
            messageTextView.setText(message);
            Log.e("CLICK", message.toString());
        }
    }





}
