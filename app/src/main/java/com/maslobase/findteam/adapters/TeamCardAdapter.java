package com.maslobase.findteam.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maslobase.findteam.R;
import com.maslobase.findteam.models.TeamPost;

import java.util.List;

/**
 * Created by Inessa on 05.11.2017.
 */

public class TeamCardAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private List<TeamPost> itemList;

    public TeamCardAdapter(List<TeamPost> itemList) {
        this.itemList = itemList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.team_cardview,viewGroup,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        TeamPost teamPost = itemList.get(position);
        holder.bind(teamPost);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}