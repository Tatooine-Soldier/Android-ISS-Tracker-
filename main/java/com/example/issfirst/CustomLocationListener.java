package com.example.issfirst;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class CustomLocationListener implements LocationListener {
    private Double latitude;
    private Double longitude;

    public CustomLocationListener() {
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    @Override
    public void onLocationChanged(@NonNull Location loc) {
//        editLocation.setText("");
//        pb.setVisibility(View.INVISIBLE);
        System.out.println("LOCATIONLISTENERlatitude"+Double.toString(loc.getLatitude())+"longitude"+Double.toString(loc.getLongitude()));
        String longitude = "Longitude: " + loc.getLongitude();
        Log.v(TAG, longitude);
        String latitude = "Latitude: " + loc.getLatitude();
        Log.v(TAG, latitude);
        this.latitude = loc.getLatitude();
        this.longitude = loc.getLongitude();
    }

    public void getLastKnownLocation(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    public Double[] getLocs() {
        return new Double[]{this.longitude, this.latitude};
    }

}
