package com.maslobase.findteam.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maslobase.findteam.R;
import com.maslobase.findteam.Utils;
import com.maslobase.findteam.models.Message;
import com.maslobase.findteam.models.Profile;

/**
 * Created by Inessa on 10.06.2018.
 */
public class SentMessageHolder extends RecyclerView.ViewHolder {

    private TextView messageText, timeText, nameText;
    private Profile authorProfile;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dataRef = database.getReference().child("users");

    public SentMessageHolder(View itemView) {
        super(itemView);
        messageText = itemView.findViewById(R.id.text_message_body);
        timeText = itemView.findViewById(R.id.text_message_time);
        dataRef.keepSynced(true);
    }

    public void bind(final Message message) {
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                authorProfile = dataSnapshot.child(message.getSender()).getValue(Profile.class);
                messageText.setText(message.getMessage());
                timeText.setText(Utils.formatDateTime(message.getCreatedAt()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
