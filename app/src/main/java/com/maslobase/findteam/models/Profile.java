package com.maslobase.findteam.models;


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

    public Profile(String steamId, String personaName, String profileUrl, String avatar, String avatarMedium, String avatarFull, String personaState, String communityVisibilityState, String profileState, String lastLogoff) {
        this.steamId = steamId;
        this.personaName = personaName;
        this.profileUrl = profileUrl;
        this.avatar = avatar;
        this.avatarMedium = avatarMedium;
        this.avatarFull = avatarFull;
        this.personaState = personaState;
        this.communityVisibilityState = communityVisibilityState;
        this.profileState = profileState;
        this.lastLogoff = lastLogoff;
    }

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
}
