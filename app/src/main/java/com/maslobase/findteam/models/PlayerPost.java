package com.maslobase.findteam.models;

import java.util.List;

/**
 * Created by Inessa on 08.11.2017.
 */

public class PlayerPost {

    private Long id;
    private String playerId;
    private String shortTitle;
    private String mmr;
    private String age;
    private List<String> roles;

    public PlayerPost() {
    }

    public PlayerPost(Long id, String playerId, String shortTitle, String mmr, String age, List<String> roles) {
        this.id = id;
        this.playerId = playerId;
        this.shortTitle = shortTitle;
        this.mmr = mmr;
        this.age = age;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
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
