package com.maslobase.findteam.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

public class ReceivedMessageHolder extends RecyclerView.ViewHolder {
    private TextView messageText, timeText, nameText;
    private ImageView profileImage;
    private View view;
    private Profile authorProfile;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dataRef = database.getReference();

    public ReceivedMessageHolder(View itemView) {
        super(itemView);
        view = itemView;
        messageText = itemView.findViewById(R.id.text_message_body);
        timeText = itemView.findViewById(R.id.text_message_time);
        nameText = itemView.findViewById(R.id.text_message_name);
        profileImage = itemView.findViewById(R.id.image_message_profile);
    }

    public void bind(final Message message) {
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                authorProfile = dataSnapshot.child("users").child(message.getSender()).getValue(Profile.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        messageText.setText(message.getMessage());

        // Format the stored timestamp into a readable String using method.
        timeText.setText(Utils.formatDateTime(message.getCreatedAt()));
        //nameText.setText(authorProfile.getPersonaname());

        // Insert the profile image from the URL into the ImageView.
        //Glide.with(view).load(authorProfile.getAvatarmedium()).into(profileImage);
    }
}
