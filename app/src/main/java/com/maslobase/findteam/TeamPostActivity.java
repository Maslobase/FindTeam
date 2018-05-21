package com.maslobase.findteam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.maslobase.findteam.models.TeamPost;

/**
 * Created by Inessa on 21.05.2018.
 */

public class TeamPostActivity extends AppCompatActivity {

    private TextView shortTitle;
    private TextView language;
    private TextView rank;
    private TextView mmr;
    private TextView hoursPlayed;
    private TextView age;
    private TextView roles;
    private TextView servers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teampost);

        shortTitle = findViewById(R.id.postTitle);
        language = findViewById(R.id.postLanguage);
        rank = findViewById(R.id.postRank);
        mmr = findViewById(R.id.postMmr);
        hoursPlayed = findViewById(R.id.postHoursPlayed);
        age = findViewById(R.id.postAge);
        roles = findViewById(R.id.postRoles);
        servers = findViewById(R.id.postServers);

        TeamPost teamPost = getIntent().getParcelableExtra("teamPost");
        shortTitle.setText(teamPost.getShortTitle());
        language.setText("Language: ".concat(teamPost.getLanguage()));
        rank.setText("Rank: ".concat(teamPost.getRank()));
        mmr.setText("Mmr: ".concat(teamPost.getMmr()));
        hoursPlayed.setText("Hours Played: ".concat(teamPost.getHoursPlayed()));
        age.setText("Age: ".concat(teamPost.getAge()));
        roles.setText("Roles: ".concat(teamPost.getRoles()));
        servers.setText("Servers: ".concat(teamPost.getServers()));

    }
}
