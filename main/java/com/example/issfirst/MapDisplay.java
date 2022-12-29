package com.example.issfirst;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.math.BigInteger;

public class MapDisplay extends AppCompatActivity implements OnMapReadyCallback {

    public static double longitude = 21;
    public static double latitude = 21;
    public static String locallongitude = "0";
    public static String locallatitude = "0";
    public Double[] locs = {0.0, 0.0};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

        Bundle local = getIntent().getExtras();
        locallongitude = local.getString("longitude");
        locallatitude = local.getString("latitude");

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googlemap) {
        try {
            longitude = Double.parseDouble(locallongitude);
            latitude = Double.parseDouble(locallatitude);
            System.out.println("\nSUCCESSFUL" +
                    longitude+latitude+"\n");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        LatLng issLoc = new LatLng(latitude, longitude);
        Marker issMarker = googlemap.addMarker(new MarkerOptions()
                .position(issLoc)
                .title("ISS"));
        assert issMarker != null;
        issMarker.setSnippet("latitude: "+locallatitude+"\nlongitude"+locallongitude);
//        issMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.iss_icon));

        googlemap.moveCamera(CameraUpdateFactory.newLatLng(issLoc));


        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            System.out.println("FAILED HERE IN PERMISSIONS");
//            ActivityCompat.requestPermissions(this, ["ACCESS_FINE_LOCATION", ]);
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double my_lat = location.getLatitude();
        double my_long = location.getLongitude();
        System.out.println("LOCATION LAT\n\n\n-->"+Double.toString(my_lat));

        LatLng userLoc = new LatLng(my_lat, my_long);
        Marker userLocMarker = googlemap.addMarker(new MarkerOptions()
                .position(userLoc)
                .title("Me"));
        assert userLocMarker != null;
        userLocMarker.setSnippet("latitude: "+my_lat+"\nlongitude"+my_long);
        userLocMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.iss_image));

    }

}
