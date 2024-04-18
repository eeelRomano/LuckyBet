package com.example.luckybet;

public class Coupon {
    private String userid;
    private String home;
    private String away;
    private String selectedteam;
    private String multiplier;
    private String stake;
    public Coupon(String userid, String home, String away, String selectedteam, String multiplier, String stake)
    {
        this.userid = userid;
        this.home = home;
        this.away = away;
        this.selectedteam = selectedteam;
        this.multiplier = multiplier;
        this.stake = stake;
    }
    public Coupon(){}
    public String getUserid() {return userid;}
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getHome() {return home;}
    public void setHome(String home) {
        this.home = home;
    }
    public String getAway() {
        return away;
    }
    public void setAway(String away) {
        this.away = away;
    }
    public String getSelectedteam() {
        return selectedteam;
    }
    public void setSelectedteam(String selectedteam) {
        this.selectedteam = selectedteam;
    }
    public String getMultiplier() {
        return multiplier;
    }
    public void setMultiplier(String multiplier) {
        this.multiplier = multiplier;
    }
    public String getStake() {
        return stake;
    }
    public void setStake(String stake) {
        this.stake = stake;
    }
}
