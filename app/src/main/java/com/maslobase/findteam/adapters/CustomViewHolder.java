package com.maslobase.findteam.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.maslobase.findteam.R;
import com.maslobase.findteam.models.TeamPost;

/**
 * Created by Inessa on 05.11.2017.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {

    private TextView shortTitle;
    private TextView mmr;
    private TextView age;
    private TextView roles;

    public CustomViewHolder(View itemView) {
        super(itemView);
        shortTitle = itemView.findViewById(R.id.short_title);
        mmr = itemView.findViewById(R.id.mmr);
        age = itemView.findViewById(R.id.age);
        //roles = itemView.findViewById(R.id.roles);
    }

    public void bind(TeamPost teamPost) {
        shortTitle.setText(teamPost.getShortTitle());
        mmr.setText(teamPost.getMmr());
        age.setText(teamPost.getAge());
        //roles.setText(teamPost.getRoles());
    }
}