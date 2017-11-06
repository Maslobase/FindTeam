package com.maslobase.findteam;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.maslobase.findteam.models.Profile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import okhttp3.HttpUrl;

public class ProfileActivity extends AppCompatActivity {

    private String userId;
    private ImageView avatarImage;
    private FloatingActionButton pmButton;
    private TextView steamIdView;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;


    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userRef = database.getReference().child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userId = getIntent().getStringExtra("userId");

        DatabaseReference usersRef = database.getReference("users");
        usersRef.push();

        toolbar = findViewById(R.id.toolbar);
        pmButton = findViewById(R.id.pmButton);
        avatarImage = findViewById(R.id.avatar);

        initNavigationView();

        LoadAvatarTask task = new LoadAvatarTask(this);
        task.execute();


    }

    private void initNavigationView() {

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.actionFindTeam:
                        browseTeams();
                        break;
                    case R.id.actionFindPlayer:
                        browsePlayers();
                        break;
                    case R.id.actionExit:
                        finish();
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void browsePlayers() {
        Intent intent = new Intent(getApplicationContext(), FindPlayerActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    private void browseTeams() {
        Intent intent = new Intent(getApplicationContext(), FindTeamActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }


    private class LoadAvatarTask extends AsyncTask<String, Void, String> {

        Activity parentActivity;
        String profileString;

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

                profileString = Utils.getJSONObjectFromURL(url.toString()).getJSONObject("response").getJSONArray("players").get(0).toString();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                return profileString;
            }
        }

        @Override
        protected void onPostExecute(String profileString) {
            //do stuff
            try {
                updateProfile(profileString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void updateProfile(String profileString) throws JSONException {
        JSONObject profileJsonObj = new JSONObject(profileString);
        Profile profile = new Profile(profileJsonObj);

        toolbar.setTitle(profile.getPersonaName());
        steamIdView = findViewById(R.id.userId);
        steamIdView.setText(profile.getSteamId());
        Glide.with(this).load(profile.getAvatarFull()).into(avatarImage);

        // create/update profile JSON in Firebase
        writeNewProfile(profile);
    }

    private void writeNewProfile(Profile profile) {
        userRef.child(userId).setValue(profile);
    }
}
