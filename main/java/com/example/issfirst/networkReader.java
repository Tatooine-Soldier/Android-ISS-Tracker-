package com.example.issfirst;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class networkReader extends AsyncTask<String, Void, String> {
    Exception e;
    public String[] locData = {"", ""};
    WeakReference<Activity> mWeakActivity;

    public networkReader(Activity activity) {
        mWeakActivity = new WeakReference<Activity>(activity);
    }

    @Override
    protected String doInBackground(String... urls) {
        String[] arr = {};
        try {
            URL url = new URL(urls[0]);
            try {
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                String contentString = content.toString();
                System.out.println(contentString);
                arr = this.parseMyJSON(contentString);
                in.close();

            } catch ( IOException e) {
                e.printStackTrace();
                this.e = e;
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
//                    Toast.makeText(this, "failed", 1).show();
        }
        return arr[0]+"$"+arr[1];
    }


    public String[] parseMyJSON(String jsonString) throws JSONException {
        if (jsonString != null) {

            JSONObject jObject = new JSONObject(jsonString);

            String longitude;
            longitude = jObject.getString("longitude");
            System.out.println(longitude);

            String latitude;
            latitude = jObject.getString("latitude");
            System.out.println(latitude);

            locData[0] = longitude;
            locData[1] = latitude;
            String[] arr = new String[]{locData[0], locData[1]};

            return arr;
        }
        return null;
    };

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Activity activity = mWeakActivity.get();
        TextView latTV = activity.findViewById(R.id.latTV);
        latTV.setText(locData[0]);
        TextView longTV = activity.findViewById(R.id.longTV);
        longTV.setText(locData[1]);

    }
}