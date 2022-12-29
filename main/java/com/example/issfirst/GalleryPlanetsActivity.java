package com.example.issfirst;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class GalleryPlanetsActivity extends AppCompatActivity {
    Context context;
    ListView l;
    Planet venus = new Planet("Venus", R.drawable.venus_app);
    Planet mars = new Planet("Mars", R.drawable.mars_app);
    Planet jupiter = new Planet("Jupiter", R.drawable.jupiter_app);
    Planet uranus = new Planet("Uranus", R.drawable.uranus_app);
    Planet neptune = new Planet("Neptune", R.drawable.neptune_app);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets_gallery);

        l = findViewById(R.id.mylist);

        Planet[] planets = {venus, mars, jupiter, uranus, neptune};

        //Integer[] imageArr = {R.drawable.venus_app, R.drawable.mars_app, R.drawable.jupiter_app, R.drawable.uranus_app, R.drawable.neptune_app};
        AdapterGallery adapter = new AdapterGallery(this, android.R.layout.simple_list_item_1, planets);
        l.setAdapter(adapter);

    }
}
