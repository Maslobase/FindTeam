package com.maslobase.findteam;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.maslobase.findteam.models.Dota2StatsPlayer;
import com.maslobase.findteam.models.Dota2StatsWL;
import com.maslobase.findteam.models.Hero;
import com.maslobase.findteam.models.Profile;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.HttpUrl;

public class ProfileActivity extends AppCompatActivity {

    private ImageView avatarView;
    private TextView usernameView;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private TextView wl;
    private TextView matches;
    private TextView mmr;
    private ImageView heroImage1;
    private ImageView heroImage2;
    private ImageView heroImage3;
    private TextView heroName1;
    private TextView heroName2;
    private TextView heroName3;
    private TextView heroGamesPlayed1;
    private TextView heroGamesPlayed2;
    private TextView heroGamesPlayed3;
    private ImageView bestHeroImage1;
    private ImageView bestHeroImage2;
    private ImageView bestHeroImage3;
    private TextView bestHeroName1;
    private TextView bestHeroName2;
    private TextView bestHeroName3;
    private TextView heroWinrate1;
    private TextView heroWinrate2;
    private TextView heroWinrate3;


    private String userId;
    private JSONArray heroesJson;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userRef = database.getReference().child("users");
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        heroesJson = Utils.parseJSONHeroes(this);

        userId = getIntent().getStringExtra("userId");
        DatabaseReference usersRef = database.getReference("users");
        usersRef.push();

        avatarView = findViewById(R.id.avatar_dashboard);
        usernameView = findViewById(R.id.username);
        wl = findViewById(R.id.wl);
        matches = findViewById(R.id.matches);
        mmr = findViewById(R.id.mmr);
        heroImage1 = findViewById(R.id.heroImage1);
        heroImage2 = findViewById(R.id.heroImage2);
        heroImage3 = findViewById(R.id.heroImage3);
        heroName1 = findViewById(R.id.heroName1);
        heroName2 = findViewById(R.id.heroName2);
        heroName3 = findViewById(R.id.heroName3);
        heroGamesPlayed1 = findViewById(R.id.heroGamesPlayed1);
        heroGamesPlayed2 = findViewById(R.id.heroGamesPlayed2);
        heroGamesPlayed3 = findViewById(R.id.heroGamesPlayed3);
        bestHeroImage1 = findViewById(R.id.bestHeroImage1);
        bestHeroImage2 = findViewById(R.id.bestHeroImage2);
        bestHeroImage3 = findViewById(R.id.bestHeroImage3);
        bestHeroName1 = findViewById(R.id.bestHeroName1);
        bestHeroName2 = findViewById(R.id.bestHeroName2);
        bestHeroName3 = findViewById(R.id.bestHeroName3);
        heroWinrate1 = findViewById(R.id.heroWinrate1);
        heroWinrate2 = findViewById(R.id.heroWinrate2);
        heroWinrate3 = findViewById(R.id.heroWinrate3);


        initNavigationView();


        LoadAvatarTask loadAvatarTask = new LoadAvatarTask(this);
        loadAvatarTask.execute();
        LoadDota2WLStatsTask loadDota2WLStatsTask = new LoadDota2WLStatsTask(this);
        loadDota2WLStatsTask.execute();
        LoadDota2PlayerStatsTask loadDota2PlayerStatsTask = new LoadDota2PlayerStatsTask(this);
        loadDota2PlayerStatsTask.execute();
        LoadDota2PlayerHeroesTask loadDota2PlayerHeroesTask = new LoadDota2PlayerHeroesTask(this);
        loadDota2PlayerHeroesTask.execute();

    }

    private void initNavigationView() {

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu, R.string.menu);
        drawerLayout.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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

        public LoadDota2WLStatsTask(ProfileActivity activity) {
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

        public LoadDota2PlayerStatsTask(ProfileActivity activity) {
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
        protected void onPostExecute(String dota2StatsPlayerString) {
            try {
                updateDota2PlayerStats(dota2StatsPlayerString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class LoadDota2PlayerHeroesTask extends AsyncTask<String, Void, String> {

        Activity parentActivity;
        String dota2HeroesPlayerString;

        public LoadDota2PlayerHeroesTask(ProfileActivity activity) {
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
                        .addPathSegment(Constants.HEROES)
                        .build().url();

                dota2HeroesPlayerString = Utils.getJSONArrayFromURL(url.toString()).toString();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                return dota2HeroesPlayerString;
            }
        }

        @Override
        protected void onPostExecute(String dota2HeroesPlayerString) {
            try {
                updateDota2PlayerMostPlayedHeroes(dota2HeroesPlayerString);
                updateDota2PlayerBestHeroes(dota2HeroesPlayerString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateDota2PlayerBestHeroes(String dota2HeroesPlayerString) throws JSONException {
        Gson gson = new Gson();
        Hero[] dota2StatsHeroes = gson.fromJson(dota2HeroesPlayerString, Hero[].class);
        List<Hero> heroesList = new ArrayList<>();
        for (int i = 0; i < dota2StatsHeroes.length; i++) {
            if (dota2StatsHeroes[i].getGames() > 4) {
                heroesList.add(dota2StatsHeroes[i]);
            }
        }
        Collections.sort(heroesList, new Comparator<Hero>() {
            @Override
            public int compare(Hero hero1, Hero hero2) {
                Float winrate1 = Float.valueOf(hero1.getWin()) / Float.valueOf(hero1.getGames());
                Float winrate2 = Float.valueOf(hero2.getWin()) / Float.valueOf(hero2.getGames());
                return winrate1.compareTo(winrate2);
            }
        });
        Collections.reverse(heroesList);

        Integer heroId1 = Integer.valueOf(heroesList.get(0).getHero_id());
        Integer heroId2 = Integer.valueOf(heroesList.get(1).getHero_id());
        Integer heroId3 = Integer.valueOf(heroesList.get(2).getHero_id());

        String heroImageFileName1 = getHeroFileName(heroId1);
        String heroImageFileName2 = getHeroFileName(heroId2);
        String heroImageFileName3 = getHeroFileName(heroId3);

        StorageReference heroesIconsRef = storageRef.child("images").child("dota").child("heroes_icons");

        StorageReference hero1IconRef = heroesIconsRef.child(heroImageFileName1.concat("_icon.png"));
        StorageReference hero2IconRef = heroesIconsRef.child(heroImageFileName2.concat("_icon.png"));
        StorageReference hero3IconRef = heroesIconsRef.child(heroImageFileName3.concat("_icon.png"));

        GlideApp.with(this)
                .load(hero1IconRef)
                .override(300, 200)
                .into(bestHeroImage1);
        GlideApp.with(this)
                .load(hero2IconRef)
                .override(300, 200)
                .into(bestHeroImage2);
        GlideApp.with(this)
                .load(hero3IconRef)
                .override(300, 200)
                .into(bestHeroImage3);

        bestHeroName1.setText(getHeroName(heroId1));
        bestHeroName2.setText(getHeroName(heroId2));
        bestHeroName3.setText(getHeroName(heroId3));

        heroWinrate1.setText(String.valueOf(BigDecimal.valueOf(Float.valueOf(heroesList.get(0).getWin()) / Float.valueOf(heroesList.get(0).getGames()) * 100.00)
                .setScale(2, RoundingMode.HALF_DOWN)).concat(" %"));
        heroWinrate2.setText(String.valueOf(BigDecimal.valueOf(Float.valueOf(heroesList.get(1).getWin()) / Float.valueOf(heroesList.get(1).getGames()) * 100.00)
                .setScale(2, RoundingMode.HALF_DOWN)).concat(" %"));
        heroWinrate3.setText(String.valueOf(BigDecimal.valueOf(Float.valueOf(heroesList.get(2).getWin()) / Float.valueOf(heroesList.get(2).getGames()) * 100.00)
                .setScale(2, RoundingMode.HALF_DOWN)).concat(" %"));
    }

    private void updateDota2PlayerMostPlayedHeroes(String dota2HeroesPlayerString) throws JSONException {
        Gson gson = new Gson();
        Hero[] dota2StatsHeroes = gson.fromJson(dota2HeroesPlayerString, Hero[].class);
        Integer heroId1 = Integer.valueOf(dota2StatsHeroes[0].getHero_id());
        Integer heroId2 = Integer.valueOf(dota2StatsHeroes[1].getHero_id());
        Integer heroId3 = Integer.valueOf(dota2StatsHeroes[2].getHero_id());

        String heroImageFileName1 = getHeroFileName(heroId1);
        String heroImageFileName2 = getHeroFileName(heroId2);
        String heroImageFileName3 = getHeroFileName(heroId3);

        StorageReference heroesIconsRef = storageRef.child("images").child("dota").child("heroes_icons");

        StorageReference hero1IconRef = heroesIconsRef.child(heroImageFileName1.concat("_icon.png"));
        StorageReference hero2IconRef = heroesIconsRef.child(heroImageFileName2.concat("_icon.png"));
        StorageReference hero3IconRef = heroesIconsRef.child(heroImageFileName3.concat("_icon.png"));

        GlideApp.with(this)
                .load(hero1IconRef)
                .override(300, 200)
                .into(heroImage1);
        GlideApp.with(this)
                .load(hero2IconRef)
                .override(300, 200)
                .into(heroImage2);
        GlideApp.with(this)
                .load(hero3IconRef)
                .override(300, 200)
                .into(heroImage3);

        heroName1.setText(getHeroName(heroId1));
        heroName2.setText(getHeroName(heroId2));
        heroName3.setText(getHeroName(heroId3));

        heroGamesPlayed1.setText(dota2StatsHeroes[0].getGames().toString().concat(" games"));
        heroGamesPlayed2.setText(dota2StatsHeroes[1].getGames().toString().concat(" games"));
        heroGamesPlayed3.setText(dota2StatsHeroes[2].getGames().toString().concat(" games"));
    }

    private String getHeroFileName(Integer heroId) throws JSONException {
        for (int i = 0; i < heroesJson.length(); i++) {
            if (heroId == heroesJson.getJSONObject(i).getInt("id")) {
                return heroesJson.getJSONObject(i).getString("filename");
            }
        }
        return null;
    }

    private String getHeroName(Integer heroId) throws JSONException {
        for (int i = 0; i < heroesJson.length(); i++) {
            if (heroId == heroesJson.getJSONObject(i).getInt("id")) {
                return heroesJson.getJSONObject(i).getString("name");
            }
        }
        return null;
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
        Profile profile = gson.fromJson(profileString, Profile.class);
        Glide.with(this).load(profile.getAvatarmedium()).into(avatarView);
        usernameView.setText(profile.getPersonaname());

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
