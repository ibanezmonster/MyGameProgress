package com.example.greg.myapplication;

/**
 * Created by Greg on 11/27/2015.
 */
public class Character {
    private String name;
    private String race;
    private int characterLevel;
    private String[] job;
    private int[] jobLevel;

    public Character(String name, String race, int characterLevel, String[] job, int[] jobLevel) {
        this.name = name;
        this.race = race;
        this.characterLevel = characterLevel;
        this.job = job;
        this.jobLevel = jobLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getCharacterLevel() {
        return characterLevel;
    }

    public void setCharacterLevel(int characterLevel) {
        this.characterLevel = characterLevel;
    }

    public String[] getJob() {
        return job;
    }

    public void setJob(String[] job) {
        this.job = job;
    }

    public int[] getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(int[] jobLevel) {
        this.jobLevel = jobLevel;
    }
}
