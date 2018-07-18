package com.example.fear5.birdobserver;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fear5.birdobserver.BirdObservations;

import java.util.List;

class BirdListAdapter extends ArrayAdapter<Obsercation> {
    private final int resource;

    public BirdListAdapter(Context context, int resource, List<Obsercation> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    public BirdListAdapter(Context context, int resource, Obsercation[] objects) {
        super(context, resource, objects);
        this.resource = resource;
    }



    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Obsercation obsercation = getItem(position);
        String nameEnglish = obsercation.getNameEnglish();
        String comment = obsercation.getComment();
        int BirdID = obsercation.getId();
        double Latitude = obsercation.getLatitude();
        double Longitude = obsercation.getLongitude();

        LinearLayout Observationview;
        if (convertView == null) {
            Observationview = new LinearLayout(getContext());

            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource, Observationview, true);
        } else {
            Observationview = (LinearLayout) convertView;
        }
        TextView nameEnglishView = Observationview.findViewById(R.id.DisplayNameEnglish);
        TextView CommentView = Observationview.findViewById(R.id.DisplayComment);
        TextView Idview = Observationview.findViewById(R.id.DisplayID);

        nameEnglishView.setText(nameEnglish);
        CommentView.setText( nameEnglish+ "     " + comment + "  ID:   " + BirdID + " LAT:  " + Latitude + "  LON:  " +Longitude);
        return Observationview;





   }
}

