package com.example.oop_project_semester2.Model;

import java.io.Serializable;

/**
 * The type Racket.
 */
public class Racket implements Serializable  {
    // Attributes
    private int racketWidth; // Width of the racket
    private int racketHeight; // Height of the racket

    /**
     * Instantiates a new Racket.
     *
     * @param width  the width of the racket
     * @param height the height of the racket
     */
// Constructor
    public Racket(int width, int height) {
        this.racketWidth = width; // Initialize racketWidth
        this.racketHeight = height; // Initialize racketHeight
    }

    /**
     * Gets racket width.
     *
     * @return the racket width
     */
// Getter for racketWidth
    public int getRacketWidth() {
        return racketWidth;
    }


    /**
     * Sets racket width.
     *
     * @param racketWidth the racket width
     */
// Setter for racketWidth
    public void setRacketWidth(int racketWidth) {
        this.racketWidth =racketWidth;
    }

    /**
     * Gets racket height.
     *
     * @return the racket height
     */
// Getter for racketHeight
    public int getRacketHeight() {
        return racketHeight;
    }


    /**
     * Sets racket height.
     *
     * @param racketHeight the racket height
     */
// Setter for racketHeight
    public void setRacketHeight(int racketHeight) {
        this.racketHeight = racketHeight;
    }
}
