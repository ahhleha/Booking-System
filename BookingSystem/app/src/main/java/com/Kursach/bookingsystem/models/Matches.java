package com.Kursach.bookingsystem.models;

public class Matches {
    long id;
    String nameFirstTeam, nameSecondTeam, dateOfGame, timeOfGame, stadium, sectorA1, sectorA2, sectorC1, sectorB1, sectorB2, sectorB3 ;




    public Matches() {
    }

    public Matches(long id, String nameFirstTeam, String nameSecondTeam, String dateOfGame, String timeOfGame, String stadium, String sectorA1, String sectorA2, String sectorC1, String sectorB1, String sectorB2, String sectorB3) {
        this.id = id;
        this.nameFirstTeam = nameFirstTeam;
        this.nameSecondTeam = nameSecondTeam;
        this.dateOfGame = dateOfGame;
        this.timeOfGame = timeOfGame;
        this.stadium = stadium;
        this.sectorA1 = sectorA1;
        this.sectorA2 = sectorA2;
        this.sectorC1 = sectorC1;
        this.sectorB1 = sectorB1;
        this.sectorB2 = sectorB2;
        this.sectorB3 = sectorB3;
    }


    public String getNameFirstTeam() {
        return nameFirstTeam;
    }

    public void setNameFirstTeam(String nameFirstTeam) {
        this.nameFirstTeam = nameFirstTeam;
    }

    public String getNameSecondTeam() {
        return nameSecondTeam;
    }

    public void setNameSecondTeam(String nameSecondTeam) {
        this.nameSecondTeam = nameSecondTeam;
    }

    public String getDateOfGame() {
        return dateOfGame;
    }

    public void setDateOfGame(String dateOfGame) {
        this.dateOfGame = dateOfGame;
    }

    public String getTimeOfGame() {
        return timeOfGame;
    }

    public void setTimeOfGame(String timeOfGame) {
        this.timeOfGame = timeOfGame;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public long getId() {
        return id;
    }


    public String getSectorA1() {
        return sectorA1;
    }

    public void setSectorA1(String sectorA1) {
        this.sectorA1 = sectorA1;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSectorA2() {
        return sectorA2;
    }

    public void setSectorA2(String sectorA2) {
        this.sectorA2 = sectorA2;
    }

    public String getSectorC1() {
        return sectorC1;
    }

    public void setSectorC1(String sectorC1) {
        this.sectorC1 = sectorC1;
    }

    public String getSectorB1() {
        return sectorB1;
    }

    public void setSectorB1(String sectorB1) {
        this.sectorB1 = sectorB1;
    }

    public String getSectorB2() {
        return sectorB2;
    }

    public void setSectorB2(String sectorB2) {
        this.sectorB2 = sectorB2;
    }

    public String getSectorB3() {
        return sectorB3;
    }

    public void setSectorB3(String sectorB3) {
        this.sectorB3 = sectorB3;
    }
}
