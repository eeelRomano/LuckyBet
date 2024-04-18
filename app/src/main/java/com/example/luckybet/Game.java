package com.example.luckybet;

public class Game {
    private String home;
    private String away;
    private String winHome;
    private String tie;
    private String winAway;
    public Game(String home, String away, String winHome, String tie, String winAway)
    {
        this.home = home;
        this.away = away;
        this.winHome = winHome;
        this.tie = tie;
        this.winAway = winAway;
    }
    public Game(){}
    public String getHome() {
        return home;
    }
    public void setHome(String home) {
        this.home = home;
    }
    public String getAway() {
        return away;
    }
    public void setAway(String away) {
        this.away = away;
    }
    public String getWinHome() {
        return winHome;
    }
    public void setWinHome(String winHome) {
        this.winHome = winHome;
    }
    public String getTie() {
        return tie;
    }
    public void setTie(String tie) {
        this.tie = tie;
    }
    public String getWinAway() {
        return winAway;
    }
    public void setWinAway(String winAway) {
        this.winAway = winAway;
    }
    public static Game GameError()
    {
        Game game;
        return game = new Game("error", "error", "error", "error", "error");
    }
}
