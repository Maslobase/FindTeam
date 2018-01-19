package com.maslobase.findteam.models;

/**
 * Created by Inessa on 19.01.2018.
 */

public class Dota2StatsWL {
    private Integer win;
    private Integer lose;

    public Dota2StatsWL() {
    }

    public Dota2StatsWL(Integer win, Integer lose) {
        this.win = win;
        this.lose = lose;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getLose() {
        return lose;
    }

    public void setLose(Integer lose) {
        this.lose = lose;
    }
}
