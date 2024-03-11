package com.example.oop_project_semester2.Model;

public class Racket {
    // Attributes
    private int racketWidth; // Width of the racket
    private int racketHeight; // Height of the racket

    // Constructor
    public Racket(int width, int height) {
        this.racketWidth = width; // Initialize racketWidth
        this.racketHeight = height; // Initialize racketHeight
    }

    // Getter for racketWidth
    public int getRacketWidth() {
        return racketWidth;
    }


    // Setter for racketWidth
    public void setRacketWidth(int racketWidth) {
        this.racketWidth =racketWidth;
    }

    // Getter for racketHeight
    public int getRacketHeight() {
        return racketHeight;
    }


    // Setter for racketHeight
    public void setRacketHeight(int racketHeight) {
        this.racketHeight = racketHeight;
    }
}
