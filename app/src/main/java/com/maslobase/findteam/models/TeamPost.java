package com.maslobase.findteam.models;

import java.util.List;

/**
 * Created by Inessa on 05.11.2017.
 */

public class TeamPost {

    private String id;
    private String author;
    private String shortTitle;
    private String mmr;
    private String age;
    private List<String> roles;

    public TeamPost(String id, String author, String shortTitle, String mmr, String age, List<String> roles) {
        this.id = id;
        this.author = author;

        this.shortTitle = shortTitle;
        this.mmr = mmr;
        this.age = age;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}