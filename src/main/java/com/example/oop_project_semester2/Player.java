package com.example.oop_project_semester2;

public class Player {
    // Attributes
    private String name; // Player's name
    private int playerScore; // Player's score

    // Constructor
    public Player(String name, int score) {
        this.name = name; // Initialize player's name
        this.playerScore = score; // Initialize player's score
    }

    // Getter for the player's name
    public String getName() {
        return name;
    }

    // Getter for the player's score
    public int getPlayerScore() {
        return playerScore;
    }

    // Setter for the player's name
    public void setName(String name) {
        this.name = name;
    }

    // Setter for the player's score
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void incrementPlayerScore(int playerScore) {
        this.playerScore++;
    }
}