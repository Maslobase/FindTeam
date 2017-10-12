package com.maslobase.findteam;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.HttpUrl;

public class ProfileActivity extends AppCompatActivity {

    private String userId = null;
    private ImageView avatarImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userId = getIntent().getStringExtra("userId");

        // set steam user id text
        TextView steamIdView = (TextView) findViewById(R.id.userId);
        steamIdView.setText(userId);

        // load avatar image
        avatarImage = (ImageView) findViewById(R.id.avatar);

        LoadAvatarTask task = new LoadAvatarTask(this);
        task.execute();


    }

    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }

    private class LoadAvatarTask extends AsyncTask<String, Void, String> {

        Activity parentActivity;
        String avatarFullUrl = null;

        public LoadAvatarTask(ProfileActivity activity) {
            this.parentActivity = activity;
        }

        @Override
        protected String doInBackground(String... params) {
            try {


                URL url = new HttpUrl.Builder()
                        .scheme("http")
                        .host(Constants.API_HOST)
                        .addPathSegments(Constants.GET_PLAYER_SUMMARIES)
                        .addQueryParameter("key", Constants.API_KEY)
                        .addQueryParameter("steamids", userId)
                        .build().url();

                avatarFullUrl = ((JSONObject) getJSONObjectFromURL(url.toString()).getJSONObject("response").getJSONArray("players").get(0)).getString("avatarfull");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return avatarFullUrl;
        }

        @Override
        protected void onPostExecute(String result) {
            //do stuff
            updateAvatar(avatarFullUrl);
        }

    }

    private void updateAvatar(String avatarFullUrl) {
        Glide.with(this).load(avatarFullUrl).into(avatarImage);
    }
}
