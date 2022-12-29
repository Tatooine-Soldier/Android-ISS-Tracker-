package com.example.issfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Button b;
    Button gallery_button;
    Button planets_button;
    Button refresh;
    TextView longTV;
    TextView latTV;
    TextView timeTV;
    String longitude;
    String latitude;
    String[] locs;
    static Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // wire
        longTV = (TextView) findViewById(R.id.longTV);
        latTV = (TextView) findViewById(R.id.latTV);
        fillCoordinates(longTV, latTV);


        refresh = (Button) findViewById(R.id.refreshButton);

        timeTV = (TextView) findViewById(R.id.timeTV);
        this.getTimeNow(timeTV);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String returnedVal = (String)msg.obj;
                System.out.println("RECEIVING TIME"+returnedVal);
                timeTV.setText(returnedVal);
            }
        };
        Thread thread = new Thread(new Runnable() {
            final Boolean running = true;
            @Override
            public void run() {
                try {
                    while (running) {
                        Thread.sleep(1000);
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();

                        String time = dateFormat.format(now);
                        Message msg = handler.obtainMessage(1, time);
                        handler.sendMessage(msg);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("FAILED");
                }
            }
        });
        thread.start();

        b = findViewById(R.id.findButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //put in a try
                String issUrl = "https://api.wheretheiss.at/v1/satellites/25544";
                try {
                    String l = new networkReader(MainActivity.this).execute(issUrl).get();
                    System.out.println("ELLL-->"+l);
                    locs = parseResult(l);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(MainActivity.this, MapDisplay.class);
                longitude = (String) longTV.getText().toString();
                latitude = (String) latTV.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("longitude", locs[0]);
                bundle.putString("latitude", locs[1]);
                intent.putExtras(bundle);
                System.out.println("sending over some --> "+locs[0]+locs[1]);
                startActivity(intent);

            }
        });

        refresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fillCoordinates(longTV, latTV);
            }
        });

        gallery_button = (Button) findViewById(R.id.galleryButton);
        gallery_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(intent);
            }
        });

    }

    public void fillCoordinates(TextView longTV, TextView latTV) {
        String issUrl = "https://api.wheretheiss.at/v1/satellites/25544";
        try {
            String l = new networkReader(MainActivity.this).execute(issUrl).get();
            locs = parseResult(l);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        longTV.setText(locs[1]);
        latTV.setText(locs[0]);
    }

    public String[] parseResult(String l) {
        String[] splitted = l.split("\\$", 2);
        return splitted;
    }

    public void getTimeNow(TextView timeTV) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String time = dateFormat.format(now);
        timeTV.setText(time);

    }
}