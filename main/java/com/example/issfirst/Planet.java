package com.example.issfirst;

import android.media.Image;

public class Planet {
    String name;
    int image;

    public Planet(String name, int image) {
        this.name = name;
        this.image = image;
    }


    public Planet stringToPlanet(String name) {
        switch (name) {
            case "Venus":
                return new Planet("Venus", R.drawable.venus_app);
            case "Mars":
                return new Planet("Mars", R.drawable.mars_app);
            case "Jupiter":
                return new Planet("Jupiter", R.drawable.jupiter_app);
            case "Uranus":
                return new Planet("Uranus", R.drawable.uranus_app);
        }
        return null;
    }
}
