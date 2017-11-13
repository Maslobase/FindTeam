package com.maslobase.findteam.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.maslobase.findteam.R;
import com.maslobase.findteam.models.PlayerPost;

/**
 * Created by Inessa on 08.11.2017.
 */

public class PlayerCardViewHolder extends RecyclerView.ViewHolder {

    private TextView shortTitle;
    private TextView mmr;
    private TextView age;
    private TextView roles;

    public PlayerCardViewHolder(View itemView) {
        super(itemView);
        shortTitle = itemView.findViewById(R.id.short_title);
        mmr = itemView.findViewById(R.id.mmr);
        age = itemView.findViewById(R.id.age);
        //roles = itemView.findViewById(R.id.roles);
    }

    public void bind(PlayerPost playerPost) {
        shortTitle.setText(playerPost.getShortTitle());
        mmr.setText(playerPost.getMmr());
        age.setText(playerPost.getAge());
        //roles.setText(teamPost.getRoles());
    }
}
