package com.example.issfirst;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class SavedGalleryActivity extends AppCompatActivity {

    ListView l;
    List<Planet> planets;

    public SavedGalleryActivity(List<Planet> planets) {
        this.planets = planets;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_gallery);

        l.findViewById(R.id.mylist);

    }

    public void addItem(Planet planet) {
        this.planets.add(planet);
        Object[] p = this.planets.toArray();
        Arrays.toString(p);

        String joinedNames = TextUtils.join(", ", this.planets);
        String[] planetNames = joinedNames.split(", ");

        Planet[] truePlanetList = {new Planet("DEMO", 1)};
        for (int i=0; i<planetNames.length;i++) {
            Planet splanet = new Planet("", 0);
            Planet truePlanet = splanet.stringToPlanet(planetNames[i]);
            this.adding(truePlanetList, truePlanet);
            i++;
        }

        AdapterGallery adapter = new AdapterGallery(this, android.R.layout.simple_list_item_1, truePlanetList);
        l.setAdapter(adapter);
    }

    public void adding(@NonNull Planet[] truePlanetList, Planet planet) {
        Planet[] result = new Planet[truePlanetList.length+1];
        for(int i = 0; i < truePlanetList.length; i++)
            result[i] = truePlanetList[i];

        result[result.length - 1] = planet;
    }
}
