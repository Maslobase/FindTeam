package com.maslobase.findteam.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.maslobase.findteam.R;
import com.maslobase.findteam.TeamPostActivity;
import com.maslobase.findteam.models.TeamPost;

/**
 * Created by Inessa on 05.11.2017.
 */

public class TeamViewHolder extends RecyclerView.ViewHolder {

    private TextView shortTitle;
    private TextView mmr;
    private TextView age;
    //private TextView roles;
    private ImageButton bookmarkButton;

    public TeamViewHolder(View itemView) {
        super(itemView);
        this.shortTitle = itemView.findViewById(R.id.short_title);
        this.mmr = itemView.findViewById(R.id.mmr);
        this.age = itemView.findViewById(R.id.age);
        //this.roles = itemView.findViewById(R.id.roles);
        this.bookmarkButton = itemView.findViewById(R.id.bookmarkButton);
    }

    public void bind(final TeamPost teamPost) {
        shortTitle.setText(teamPost.getShortTitle());
        mmr.setText(teamPost.getMmr());
        age.setText(teamPost.getAge());
        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: add post to bookmarks
            }
        });
        //roles.setText(teamPost.getRoles());
    }
}