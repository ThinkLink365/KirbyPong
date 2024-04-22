/**
 * The Builder class constructs Player objects with specified attributes.
 */
package com.example.oop_project_semester2.Controller;

import com.example.oop_project_semester2.Model.Player;

public class Builder {
    private String name;
    private int playerScore;
    private int finalScore;

    /**
     * Sets the name of the player.
     * @param name The name of the player.
     * @return The Builder object.
     */
    public Builder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the score of the player.
     * @param playerScore The score of the player.
     * @return The Builder object.
     */
    public Builder setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
        return this;
    }

    /**
     * Sets the final score of the player.
     * @param finalScore The final score of the player.
     */
    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    /**
     * Builds and returns a Player object with the specified attributes.
     * @return The constructed Player object.
     */
    public Player build() {
        return new Player(name, playerScore, finalScore);
    }
}
