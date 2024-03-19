package com.example.oop_project_semester2.Model;

public class Player {
    // Attributes
    private String name; // Player's name
    private int playerScore; // Player's score

    private int finalscore; // Final score

    // Constructor
    public Player(String name, int score, int endScore) {
        this.name = name; // Initialize player's name
        this.playerScore = score; // Initialize player's score
        this.finalscore = endScore;

    }

    // Getter for the player's name
    public String getName() {
        return name;
    }

    // Getter for the player's score
    public int getPlayerScore() {
        return playerScore;
    }

    public int getFinalscore() {
        return finalscore;
    }

    // Setter for the player's name
    public void setName(String name) {
        this.name = name;
    }

    // Setter for the player's score
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void setFinalscore(int finalscore) {
        this.finalscore = finalscore;
    }

    public void incrementPlayerScore() {
        this.playerScore++;
    }
}