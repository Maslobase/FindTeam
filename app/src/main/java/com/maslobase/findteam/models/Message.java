package com.maslobase.findteam.models;

/**
 * Created by Inessa on 10.06.2018.
 */

public class Message {
    private String message;
    private String sender;
    private long createdAt;

    public Message() {
    }

    public Message(String message, String sender, long createdAt) {
        this.message = message;
        this.sender = sender;
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
