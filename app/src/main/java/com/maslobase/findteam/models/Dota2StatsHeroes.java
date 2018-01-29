package com.maslobase.findteam.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Inessa on 19.01.2018.
 */

public class Dota2StatsHeroes {
    private List<Hero> heroes;

    public Dota2StatsHeroes() {
        
    }

    public Dota2StatsHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public Dota2StatsHeroes(JSONArray heroesArray) throws JSONException {
        List<Hero> heroes = new ArrayList<>();
        for (int i = 0; i < heroesArray.length(); i++) {
            heroes.add(new Hero((JSONObject) heroesArray.get(i)));
        }
        this.heroes = heroes;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }
}
