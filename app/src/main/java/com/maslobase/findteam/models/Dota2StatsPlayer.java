package com.maslobase.findteam.models;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Inessa on 19.01.2018.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dota2StatsPlayer {
    private String tracked_until;
    private String solo_competitive_rank;
    private String competitive_rank;
    private Integer rank_tier;
    private Integer leaderboard_rank;
    private MmrEstimate mmr_estimate;

    public Dota2StatsPlayer() {

    }

    public Dota2StatsPlayer(String tracked_until, String solo_competitive_rank, String competitive_rank, Integer rank_tier, Integer leaderboard_rank, MmrEstimate mmr_estimate) {
        this.tracked_until = tracked_until;
        this.solo_competitive_rank = solo_competitive_rank;
        this.competitive_rank = competitive_rank;
        this.rank_tier = rank_tier;
        this.leaderboard_rank = leaderboard_rank;
        this.mmr_estimate = mmr_estimate;
    }

    public String getTracked_until() {
        return tracked_until;
    }

    public void setTracked_until(String tracked_until) {
        this.tracked_until = tracked_until;
    }

    public String getSolo_competitive_rank() {
        return solo_competitive_rank;
    }

    public void setSolo_competitive_rank(String solo_competitive_rank) {
        this.solo_competitive_rank = solo_competitive_rank;
    }

    public String getCompetitive_rank() {
        return competitive_rank;
    }

    public void setCompetitive_rank(String competitive_rank) {
        this.competitive_rank = competitive_rank;
    }

    public Integer getRank_tier() {
        return rank_tier;
    }

    public void setRank_tier(Integer rank_tier) {
        this.rank_tier = rank_tier;
    }

    public Integer getLeaderboard_rank() {
        return leaderboard_rank;
    }

    public void setLeaderboard_rank(Integer leaderboard_rank) {
        this.leaderboard_rank = leaderboard_rank;
    }

    public MmrEstimate getMmr_estimate() {
        return mmr_estimate;
    }

    public void setMmr_estimate(MmrEstimate mmr_estimate) {
        this.mmr_estimate = mmr_estimate;
    }
}
