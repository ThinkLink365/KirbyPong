package com.example.oop_project_semester2;

public class Player {
    private String name;
    private int playerScore;

    public Player(String name, int score) {
        this.name = name;
        this.playerScore = score;
    }

    public String getName() {
        return name;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
