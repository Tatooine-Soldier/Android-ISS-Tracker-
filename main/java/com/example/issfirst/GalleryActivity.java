package com.example.issfirst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class GalleryActivity extends AppCompatActivity {

    Button planetsButton;
    Button galaxiesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        planetsButton = findViewById(R.id.galleryButton1);
        galaxiesButton = findViewById(R.id.galleryButton2);

        planetsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GalleryActivity.this, GalleryPlanetsActivity.class);
                startActivity(intent);
            }
        });











        URL url = null;
        try {
            url = new URL("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection con = null;
            try {
                con = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                con.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }

            BufferedReader in = null;
            try {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String inputLine = "";
            StringBuffer content = new StringBuffer();
            while (true) {
                try {
                    if (!((inputLine = in.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                content.append(inputLine);
            }
            String contentString = content.toString();
            System.out.println(contentString);
            //String message = this.parseMyJSON(contentString);
    } catch (Exception e) {
        e.printStackTrace();
        }
    }
//    public String parseMyJSON(String jsonString) throws JSONException {
//        if (jsonString != null) {
//
//            JSONObject jObject = new JSONObject(jsonString);
//        } else {
//
//        }

}
