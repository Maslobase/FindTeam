package com.maslobase.findteam.models;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Inessa on 15.10.2017.
 */


public class Profile {

    private String steamId;
    private String personaName;
    private String profileUrl;
    private String avatar;
    private String avatarMedium;
    private String avatarFull;
    private String personaState;
    private String communityVisibilityState;
    private String profileState;
    private String lastLogoff;

    public String getSteamId() {
        return steamId;
    }

    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }

    public String getPersonaName() {
        return personaName;
    }

    public void setPersonaName(String personaName) {
        this.personaName = personaName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarMedium() {
        return avatarMedium;
    }

    public void setAvatarMedium(String avatarMedium) {
        this.avatarMedium = avatarMedium;
    }

    public String getAvatarFull() {
        return avatarFull;
    }

    public void setAvatarFull(String avatarFull) {
        this.avatarFull = avatarFull;
    }

    public String getPersonaState() {
        return personaState;
    }

    public void setPersonaState(String personaState) {
        this.personaState = personaState;
    }

    public String getCommunityVisibilityState() {
        return communityVisibilityState;
    }

    public void setCommunityVisibilityState(String communityVisibilityState) {
        this.communityVisibilityState = communityVisibilityState;
    }

    public String getProfileState() {
        return profileState;
    }

    public void setProfileState(String profileState) {
        this.profileState = profileState;
    }

    public String getLastLogoff() {
        return lastLogoff;
    }

    public void setLastLogoff(String lastLogoff) {
        this.lastLogoff = lastLogoff;
    }

    public Profile() {
    }

    public Profile(JSONObject profileObj) throws JSONException {
        this.steamId = profileObj.getString("steamId").toString();
        this.personaName = profileObj.getString("personaname").toString();
        this.profileUrl = profileObj.getString("profileurl").toString();
        this.avatar = profileObj.getString("avatar").toString();
        this.avatarMedium = profileObj.getString("avatarmedium").toString();
        this.avatarFull = profileObj.getString("avatarfull").toString();
        this.personaState = profileObj.getString("personastate").toString();
        this.communityVisibilityState = profileObj.getString("communityvisibilitystate").toString();
        this.profileState = profileObj.getString("profilestate").toString();
        this.lastLogoff = profileObj.getString("lastlogoff").toString();
    }
}
