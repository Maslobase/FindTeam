package com.maslobase.findteam.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "steamid",
        "communityvisibilitystate",
        "profilestate",
        "personaname",
        "lastlogoff",
        "profileurl",
        "avatar",
        "avatarmedium",
        "avatarfull",
        "personastate",
        "primaryclanid",
        "timecreated",
        "personastateflags",
        "loccountrycode",
        "locstatecode",
        "loccityid"
})
public class Profile {

    @JsonProperty("steamid")
    private String steamid;
    @JsonProperty("communityvisibilitystate")
    private Integer communityvisibilitystate;
    @JsonProperty("profilestate")
    private Integer profilestate;
    @JsonProperty("personaname")
    private String personaname;
    @JsonProperty("lastlogoff")
    private Integer lastlogoff;
    @JsonProperty("profileurl")
    private String profileurl;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("avatarmedium")
    private String avatarmedium;
    @JsonProperty("avatarfull")
    private String avatarfull;
    @JsonProperty("personastate")
    private Integer personastate;
    @JsonProperty("primaryclanid")
    private String primaryclanid;
    @JsonProperty("timecreated")
    private Integer timecreated;
    @JsonProperty("personastateflags")
    private Integer personastateflags;
    @JsonProperty("loccountrycode")
    private String loccountrycode;
    @JsonProperty("locstatecode")
    private String locstatecode;
    @JsonProperty("loccityid")
    private Integer loccityid;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("steamid")
    public String getSteamid() {
        return steamid;
    }

    @JsonProperty("steamid")
    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    @JsonProperty("communityvisibilitystate")
    public Integer getCommunityvisibilitystate() {
        return communityvisibilitystate;
    }

    @JsonProperty("communityvisibilitystate")
    public void setCommunityvisibilitystate(Integer communityvisibilitystate) {
        this.communityvisibilitystate = communityvisibilitystate;
    }

    @JsonProperty("profilestate")
    public Integer getProfilestate() {
        return profilestate;
    }

    @JsonProperty("profilestate")
    public void setProfilestate(Integer profilestate) {
        this.profilestate = profilestate;
    }

    @JsonProperty("personaname")
    public String getPersonaname() {
        return personaname;
    }

    @JsonProperty("personaname")
    public void setPersonaname(String personaname) {
        this.personaname = personaname;
    }

    @JsonProperty("lastlogoff")
    public Integer getLastlogoff() {
        return lastlogoff;
    }

    @JsonProperty("lastlogoff")
    public void setLastlogoff(Integer lastlogoff) {
        this.lastlogoff = lastlogoff;
    }

    @JsonProperty("profileurl")
    public String getProfileurl() {
        return profileurl;
    }

    @JsonProperty("profileurl")
    public void setProfileurl(String profileurl) {
        this.profileurl = profileurl;
    }

    @JsonProperty("avatar")
    public String getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @JsonProperty("avatarmedium")
    public String getAvatarmedium() {
        return avatarmedium;
    }

    @JsonProperty("avatarmedium")
    public void setAvatarmedium(String avatarmedium) {
        this.avatarmedium = avatarmedium;
    }

    @JsonProperty("avatarfull")
    public String getAvatarfull() {
        return avatarfull;
    }

    @JsonProperty("avatarfull")
    public void setAvatarfull(String avatarfull) {
        this.avatarfull = avatarfull;
    }

    @JsonProperty("personastate")
    public Integer getPersonastate() {
        return personastate;
    }

    @JsonProperty("personastate")
    public void setPersonastate(Integer personastate) {
        this.personastate = personastate;
    }

    @JsonProperty("primaryclanid")
    public String getPrimaryclanid() {
        return primaryclanid;
    }

    @JsonProperty("primaryclanid")
    public void setPrimaryclanid(String primaryclanid) {
        this.primaryclanid = primaryclanid;
    }

    @JsonProperty("timecreated")
    public Integer getTimecreated() {
        return timecreated;
    }

    @JsonProperty("timecreated")
    public void setTimecreated(Integer timecreated) {
        this.timecreated = timecreated;
    }

    @JsonProperty("personastateflags")
    public Integer getPersonastateflags() {
        return personastateflags;
    }

    @JsonProperty("personastateflags")
    public void setPersonastateflags(Integer personastateflags) {
        this.personastateflags = personastateflags;
    }

    @JsonProperty("loccountrycode")
    public String getLoccountrycode() {
        return loccountrycode;
    }

    @JsonProperty("loccountrycode")
    public void setLoccountrycode(String loccountrycode) {
        this.loccountrycode = loccountrycode;
    }

    @JsonProperty("locstatecode")
    public String getLocstatecode() {
        return locstatecode;
    }

    @JsonProperty("locstatecode")
    public void setLocstatecode(String locstatecode) {
        this.locstatecode = locstatecode;
    }

    @JsonProperty("loccityid")
    public Integer getLoccityid() {
        return loccityid;
    }

    @JsonProperty("loccityid")
    public void setLoccityid(Integer loccityid) {
        this.loccityid = loccityid;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}