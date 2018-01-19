package com.maslobase.findteam;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.google.gson.Gson;
import com.maslobase.findteam.models.Dota2StatsPlayer;
import com.maslobase.findteam.models.Dota2StatsWL;
import com.maslobase.findteam.models.Profile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import okhttp3.HttpUrl;

public class MainActivity extends AppCompatActivity {

    private ImageView avatarView;
    private TextView usernameView;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private TextView wl;
    private TextView matches;
    private TextView mmr;

    private String userId;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userRef = database.getReference().child("users");
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userId = getIntent().getStringExtra("userId");
        DatabaseReference usersRef = database.getReference("users");
        usersRef.push();

        avatarView = findViewById(R.id.avatar_dashboard);
        usernameView = findViewById(R.id.username);
        wl = findViewById(R.id.wl);
        matches = findViewById(R.id.matches);
        mmr = findViewById(R.id.mmr);

        initNavigationView();


        LoadAvatarTask loadAvatarTask = new LoadAvatarTask(this);
        loadAvatarTask.execute();
        LoadDota2WLStatsTask loadDota2WLStatsTask = new LoadDota2WLStatsTask(this);
        loadDota2WLStatsTask.execute();
        LoadDota2PlayerStatsTask loadDota2PlayerStatsTask = new LoadDota2PlayerStatsTask(this);
        loadDota2PlayerStatsTask.execute();

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

    private class LoadAvatarTask extends AsyncTask<String, Void, String> {

        Activity parentActivity;
        String profileString;

        public LoadAvatarTask(MainActivity activity) {
            this.parentActivity = activity;
        }

        @Override
        protected String doInBackground(String... params) {
            try {


                URL url = new HttpUrl.Builder()
                        .scheme("http")
                        .host(Constants.STEAM_HOST)
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
            try {
                updateProfile(profileString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private class LoadDota2WLStatsTask extends AsyncTask<String, Void, String> {

        Activity parentActivity;
        String dota2StatsWLString;

        public LoadDota2WLStatsTask(MainActivity activity) {
            this.parentActivity = activity;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                URL url = new HttpUrl.Builder()
                        .scheme("https")
                        .host(Constants.OPENDOTA_HOST)
                        .addPathSegment(Constants.API)
                        .addPathSegment(Constants.PLAYERS)
                        .addPathSegment(Utils.steamId64ToSteamId32(userId))
                        .addPathSegment(Constants.WL)
                        .build().url();

                dota2StatsWLString = Utils.getJSONObjectFromURL(url.toString()).toString();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                return dota2StatsWLString;
            }
        }

        @Override
        protected void onPostExecute(String dota2StatsWLString) {
            try {
                updateDota2WLStats(dota2StatsWLString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class LoadDota2PlayerStatsTask extends AsyncTask<String, Void, String> {

        Activity parentActivity;
        String dota2StatsPlayerString;

        public LoadDota2PlayerStatsTask(MainActivity activity) {
            this.parentActivity = activity;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                URL url = new HttpUrl.Builder()
                        .scheme("https")
                        .host(Constants.OPENDOTA_HOST)
                        .addPathSegment(Constants.API)
                        .addPathSegment(Constants.PLAYERS)
                        .addPathSegment(Utils.steamId64ToSteamId32(userId))
                        .build().url();

                dota2StatsPlayerString = Utils.getJSONObjectFromURL(url.toString()).toString();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                return dota2StatsPlayerString;
            }
        }

        @Override
        protected void onPostExecute(String dota2StatsWLString) {
            try {
                updateDota2PlayerStats(dota2StatsWLString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateDota2PlayerStats(String dota2StatsPlayerString) throws JSONException {
        Gson gson = new Gson();
        Dota2StatsPlayer dota2StatsPlayer = gson.fromJson(dota2StatsPlayerString, Dota2StatsPlayer.class);
        mmr.setText(dota2StatsPlayer.getMmr_estimate().getEstimate().toString());
    }

    private void updateDota2WLStats(String dota2StatsWLString) throws JSONException {
        Gson gson = new Gson();
        Dota2StatsWL dota2StatsWL = gson.fromJson(dota2StatsWLString, Dota2StatsWL.class);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(dota2StatsWL.getWin())
                .append(" / ")
                .append(dota2StatsWL.getLose());
        wl.setText(stringBuilder.toString());
        Integer matchesSum = dota2StatsWL.getWin() + dota2StatsWL.getLose();
        matches.setText(matchesSum.toString());
    }

    private void updateProfile(String profileString) throws JSONException {
        Gson gson = new Gson();
        // FIXME: deserialization fails!
        Profile profile = gson.fromJson(profileString, Profile.class);
        Glide.with(this).load(profile.getAvatar()).into(avatarView);
        usernameView.setText(profile.getPersonaName());

        // create/update profile JSON in Firebase
        writeNewProfile(profile);
    }

    private void writeNewProfile(Profile profile) {
        userRef.child(userId).setValue(profile);
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
}
