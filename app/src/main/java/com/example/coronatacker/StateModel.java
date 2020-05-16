package com.example.coronatacker;

public class StateModel {

    private String active,confirmed,deaths,stateName,recovered,lastUpdated;

    public StateModel() {

    }

    public StateModel(String active, String confirmed, String deaths, String stateName, String recovered, String lastUpdated) {
        this.active = active;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.stateName = stateName;
        this.recovered = recovered;
        this.lastUpdated = lastUpdated;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
