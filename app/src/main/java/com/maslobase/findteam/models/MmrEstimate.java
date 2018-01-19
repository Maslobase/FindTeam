package com.maslobase.findteam.models;

/**
 * Created by Inessa on 20.01.2018.
 */

public class MmrEstimate {
    private Integer estimate;
    private Integer stdDev;
    private Integer n;

    public MmrEstimate() {

    }

    public MmrEstimate(Integer estimate, Integer stdDev, Integer n) {
        this.estimate = estimate;
        this.stdDev = stdDev;
        this.n = n;
    }

    public Integer getEstimate() {
        return estimate;
    }

    public void setEstimate(Integer estimate) {
        this.estimate = estimate;
    }

    public Integer getStdDev() {
        return stdDev;
    }

    public void setStdDev(Integer stdDev) {
        this.stdDev = stdDev;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }
}
