package com.maslobase.findteam.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maslobase.findteam.R;
import com.maslobase.findteam.holders.PlayerCardViewHolder;
import com.maslobase.findteam.models.PlayerPost;

import java.util.List;

/**
 * Created by Inessa on 08.11.2017.
 */

public class PlayerCardAdapter extends RecyclerView.Adapter<PlayerCardViewHolder> {

    private List<PlayerPost> itemList;

    public PlayerCardAdapter(List<PlayerPost> itemList) {
        this.itemList = itemList;
    }

    @Override
    public PlayerCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_team, viewGroup, false);
        return new PlayerCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerCardViewHolder holder, int position) {
        PlayerPost playerPost = itemList.get(position);
        holder.bind(playerPost);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
