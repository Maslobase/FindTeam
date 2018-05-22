package com.maslobase.findteam.models;

/**
 * Created by Inessa on 22.05.2018.
 */

public class Team {

    private Long id;
    private String avatar;
    private String name;

    public Team() {
    }

    public Team(Long id, String avatar, String name) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
