package com.maslobase.findteam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maslobase.findteam.adapters.TeamCardAdapter;
import com.maslobase.findteam.models.TeamPost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Inessa on 02.11.2017.
 */

public class FindTeamActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<TeamPost> teamPosts = new ArrayList<>();

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference teamPostsRef = database.getReference().child("teamPosts");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findteam);

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final TeamCardAdapter adapter = new TeamCardAdapter(teamPosts);
        recyclerView.setAdapter(adapter);

        teamPostsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                teamPosts.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    TeamPost teamPost = postSnapshot.getValue(TeamPost.class);
                    teamPosts.add(teamPost);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
