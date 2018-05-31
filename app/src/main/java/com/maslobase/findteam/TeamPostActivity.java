package com.maslobase.findteam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maslobase.findteam.models.Team;
import com.maslobase.findteam.models.TeamPost;

/**
 * Created by Inessa on 21.05.2018.
 */

public class TeamPostActivity extends AppCompatActivity {

    private ImageView teamAvatarImage;
    private TextView teamName;
    private TextView shortTitle;
    private TextView language;
    private TextView rank;
    private TextView mmr;
    private TextView hoursPlayed;
    private TextView age;
    private TextView roles;
    private TextView servers;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference teamsRef = database.getReference().child("teams");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teampost);

        teamAvatarImage = findViewById(R.id.teamAvatarImage);
        teamName = findViewById(R.id.teamName);
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
        language.setText(teamPost.getLanguage());
        rank.setText(teamPost.getRank());
        mmr.setText(teamPost.getMmr());
        hoursPlayed.setText(teamPost.getHoursPlayed());
        age.setText(teamPost.getAge());
        roles.setText(teamPost.getRoles());
        servers.setText(teamPost.getServers());

        teamsRef.child(teamPost.getId().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Team team = dataSnapshot.getValue(Team.class);
                updateTeamInfo(team);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void updateTeamInfo(Team team) {
        teamName.setText(team.getName());
        Glide.with(this).load(team.getAvatar()).into(teamAvatarImage);
    }
}
