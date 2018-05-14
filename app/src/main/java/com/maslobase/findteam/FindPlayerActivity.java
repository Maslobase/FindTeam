package com.maslobase.findteam;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maslobase.findteam.adapters.PlayerCardAdapter;
import com.maslobase.findteam.models.PlayerPost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Inessa on 02.11.2017.
 */

public class FindPlayerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private List<PlayerPost> playerPosts = new ArrayList<>();

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference playerPostsRef = database.getReference().child("playerPosts");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findplayer);

        recyclerView = findViewById(R.id.recycler_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final PlayerCardAdapter adapter = new PlayerCardAdapter(playerPosts);
        recyclerView.setAdapter(adapter);

        playerPostsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                playerPosts.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    PlayerPost playerPost = postSnapshot.getValue(PlayerPost.class);
                    playerPosts.add(playerPost);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
