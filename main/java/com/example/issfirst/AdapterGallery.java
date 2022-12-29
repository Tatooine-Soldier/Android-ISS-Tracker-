package com.example.issfirst;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class AdapterGallery extends ArrayAdapter {
    Context context;
    Planet[] planets;
    Button saved;

    public AdapterGallery(Context context, int layoutToInflate, Planet[] planets) {
        super(context, layoutToInflate, planets);
        this.context = context;
        this.planets = planets;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup group) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.layout_list_items, null);

        TextView name = row.findViewById(R.id.planetNameTV);
        ImageView image = row.findViewById(R.id.planetImageIV);

        name.setText(planets[pos].name);
        image.setImageResource(planets[pos].image);

        saved = row.findViewById(R.id.savedButton);
        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Planet> output = Arrays.asList(planets);
                SavedGalleryActivity gal = new SavedGalleryActivity(output);
                gal.addItem(planets[pos]);
            }
        });

        return (row);
    }
}
