package com.maslobase.findteam.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maslobase.findteam.R;
import com.maslobase.findteam.TeamPostActivity;
import com.maslobase.findteam.models.TeamPost;

import java.util.List;

/**
 * Created by Inessa on 05.11.2017.
 */

public class TeamCardAdapter extends RecyclerView.Adapter<TeamViewHolder> {

    private List<TeamPost> itemList;
    private View view;
    private Context context;

    public TeamCardAdapter(List<TeamPost> itemList) {
        this.itemList = itemList;
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        view = LayoutInflater.from(context).inflate(R.layout.cardview_team,viewGroup,false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        final TeamPost teamPost = itemList.get(position);
        holder.bind(teamPost);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TeamPostActivity.class);
                intent.putExtra("teamPost", teamPost);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}