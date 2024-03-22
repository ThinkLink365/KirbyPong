package com.example.oop_project_semester2.Model;

import java.io.Serializable;

/**
 * The type Player.
 */
public class Player implements Serializable {
    // Attributes
    private String name; // Player's name
    private int playerScore; // Player's score

    private int finalscore; // Final score

    /**
     * Instantiates a new Player.
     *
     * @param name     the name
     * @param score    the score
     * @param endScore the end score
     */
// Constructor
    public Player(String name, int score, int endScore) {
        this.name = name; // Initialize player's name
        this.playerScore = score; // Initialize player's score
        this.finalscore = endScore;

    }

    /**
     * Gets name.
     *
     * @return the name
     */
// Getter for the player's name
    public String getName() {
        return name;
    }

    /**
     * Gets player score.
     *
     * @return the player score
     */
// Getter for the player's score
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Gets finalscore.
     *
     * @return the finalscore
     */
    public int getFinalscore() {
        return finalscore;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
// Setter for the player's name
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets player score.
     *
     * @param playerScore the player score
     */
// Setter for the player's score
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    /**
     * Sets finalscore.
     *
     * @param finalscore the finalscore
     */
    public void setFinalscore(int finalscore) {
        this.finalscore = finalscore;
    }

    /**
     * Increment player score.
     */
    public void incrementPlayerScore() {
        this.playerScore++;
    }
}