package com.maslobase.findteam;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.maslobase.findteam.models.Profile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import okhttp3.HttpUrl;

public class ProfileActivity extends AppCompatActivity {

    private String userId = null;
    private ImageView avatarImage = null;
    private FloatingActionButton pmButton = null;
    private TextView steamIdView = null;
    private Toolbar toolbar = null;
    private Profile profile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userId = getIntent().getStringExtra("userId");

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        // set steam user id text
        //steamIdView = (TextView) findViewById(R.id.userId);
        //steamIdView.setText(userId);

        // set pmButton
        pmButton = (FloatingActionButton) findViewById(R.id.pmButton);

        // load avatar image
        avatarImage = (ImageView) findViewById(R.id.avatar);

        LoadAvatarTask task = new LoadAvatarTask(this);
        task.execute();


    }


    private class LoadAvatarTask extends AsyncTask<String, Void, Profile> {

        Activity parentActivity;

        public LoadAvatarTask(ProfileActivity activity) {
            this.parentActivity = activity;
        }

        @Override
        protected Profile doInBackground(String... params) {
            try {


                URL url = new HttpUrl.Builder()
                        .scheme("http")
                        .host(Constants.API_HOST)
                        .addPathSegments(Constants.GET_PLAYER_SUMMARIES)
                        .addQueryParameter("key", Constants.API_KEY)
                        .addQueryParameter("steamids", userId)
                        .build().url();

                // FIXME
                JSONObject profileObj = new JSONObject(Utils.getJSONObjectFromURL(url.toString()).getJSONObject("response").getJSONArray("players").get(0).toString());
                return new Profile(profileObj);
                //avatarFullUrl = ((JSONObject) Utils.getJSONObjectFromURL(url.toString()).getJSONObject("response").getJSONArray("players").get(0)).getString("avatarfull");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Profile steamProfile) {
            //do stuff
            updateProfile(steamProfile);
        }

    }

    private void updateProfile(Profile steamProfile) {
        //toolbar.setTitle(steamProfile.getPersonaName());
        steamIdView = (TextView) findViewById(R.id.userId);
        //steamIdView.setText(steamProfile.getSteamId());
        //steamIdView.setText(steamProfile.getAvatarFull());
        //Glide.with(this).load(steamProfile.getAvatarFull()).into(avatarImage);

        // TODO: create/update profile JSON in Firebase

    }
}
