package com.Kursach.bookingsystem;

public class Order {
    private String idOfMatch;
    private String userURL;
    private String sectorName;
    private String valueOfSeats;
    private String firstTeam;
    private String secondTeam;
    public Order() {
    }

    public Order(String idOfMatch, String userURL, String sectorName, String valueOfSeats, String firstTeam, String secondTeam) {
        this.idOfMatch = idOfMatch;
        this.userURL = userURL;
        this.sectorName = sectorName;
        this.valueOfSeats = valueOfSeats;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public String getIdOfMatch() {
        return idOfMatch;
    }

    public void setIdOfMatch(String idOfMatch) {
        this.idOfMatch = idOfMatch;
    }

    public String getUserURL() {
        return userURL;
    }

    public void setUserURL(String userURL) {
        this.userURL = userURL;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getValueOfSeats() {
        return valueOfSeats;
    }

    public void setValueOfSeats(String valueOfSeats) {
        this.valueOfSeats = valueOfSeats;
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }
}
