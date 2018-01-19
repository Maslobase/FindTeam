package com.maslobase.findteam.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Inessa on 19.01.2018.
 */

public class Hero {
    private String hero_id;
    private Date last_played;
    private Integer games;
    private Integer win;
    private Integer with_games;
    private Integer with_win;
    private Integer against_games;
    private Integer against_win;

    public Hero() {

    }

    public String getHero_id() {
        return hero_id;
    }

    public void setHero_id(String hero_id) {
        this.hero_id = hero_id;
    }

    public Date getLast_played() {
        return last_played;
    }

    public void setLast_played(Date last_played) {
        this.last_played = last_played;
    }

    public Integer getGames() {
        return games;
    }

    public void setGames(Integer games) {
        this.games = games;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getWith_games() {
        return with_games;
    }

    public void setWith_games(Integer with_games) {
        this.with_games = with_games;
    }

    public Integer getWith_win() {
        return with_win;
    }

    public void setWith_win(Integer with_win) {
        this.with_win = with_win;
    }

    public Integer getAgainst_games() {
        return against_games;
    }

    public void setAgainst_games(Integer against_games) {
        this.against_games = against_games;
    }

    public Integer getAgainst_win() {
        return against_win;
    }

    public void setAgainst_win(Integer against_win) {
        this.against_win = against_win;
    }

    public Hero(String hero_id, Date last_played, Integer games, Integer win, Integer with_games, Integer with_win, Integer against_games, Integer against_win) {
        this.hero_id = hero_id;
        this.last_played = last_played;
        this.games = games;
        this.win = win;
        this.with_games = with_games;
        this.with_win = with_win;
        this.against_games = against_games;
        this.against_win = against_win;
    }

    public Hero(JSONObject heroObj) throws JSONException {
        this.hero_id = heroObj.getString("hero_id");
        this.last_played = new Date(heroObj.getLong("last_played"));
        this.games = heroObj.getInt("games");
        this.win = heroObj.getInt("win");
        this.with_games = heroObj.getInt("with_games");
        this.with_win = heroObj.getInt("with_win");
        this.against_games = heroObj.getInt("against_games");
        this.against_win = heroObj.getInt("against_win");
    }
}
