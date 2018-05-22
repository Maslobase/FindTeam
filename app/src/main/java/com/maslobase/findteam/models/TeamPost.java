package com.maslobase.findteam.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Inessa on 05.11.2017.
 */

public class TeamPost implements Parcelable {

    private Long id;
    private String author;
    private String shortTitle;
    private String language;
    private String mmr;
    private String age;
    private String roles;
    private String hoursPlayed;
    private String rank;
    private String servers;
    private String text;
    private Long teamId;

    public TeamPost() {

    }

    public TeamPost(Long id, String author, String shortTitle, String language, String mmr, String age,
                    String roles, String hoursPlayed, String rank, String servers,
                    String text, Long teamId) {
        this.id = id;
        this.author = author;
        this.shortTitle = shortTitle;
        this.language = language;
        this.mmr = mmr;
        this.age = age;
        this.roles = roles;
        this.hoursPlayed = hoursPlayed;
        this.rank = rank;
        this.servers = servers;
        this.text = text;
        this.teamId = teamId;
    }

    public TeamPost(Parcel parcel) {
        this.id = parcel.readLong();
        this.author = parcel.readString();
        this.shortTitle = parcel.readString();
        this.language = parcel.readString();
        this.mmr = parcel.readString();
        this.age = parcel.readString();
        this.roles = parcel.readString();
        this.hoursPlayed = parcel.readString();
        this.rank = parcel.readString();
        this.servers = parcel.readString();
        this.text = parcel.readString();
        this.teamId = parcel.readLong();
    }

    public static final Creator<TeamPost> CREATOR = new Creator<TeamPost>() {
        @Override
        public TeamPost createFromParcel(Parcel in) {
            return new TeamPost(in);
        }

        @Override
        public TeamPost[] newArray(int size) {
            return new TeamPost[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMmr() {
        return mmr;
    }

    public void setMmr(String mmr) {
        this.mmr = mmr;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(String hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(author);
        parcel.writeString(shortTitle);
        parcel.writeString(language);
        parcel.writeString(mmr);
        parcel.writeString(age);
        parcel.writeString(roles);
        parcel.writeString(hoursPlayed);
        parcel.writeString(rank);
        parcel.writeString(servers);
        parcel.writeString(text);
        parcel.writeLong(teamId);
    }
}