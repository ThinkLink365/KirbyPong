package com.example.oop_project_semester2;

import javafx.beans.property.SimpleIntegerProperty;

public class Racket {
    // Attributes
    private final SimpleIntegerProperty racketWidth; // Width of the racket
    private final SimpleIntegerProperty racketHeight; // Height of the racket

    // Constructor
    public Racket(int width, int height) {
        this.racketWidth = new SimpleIntegerProperty(width); // Initialize racketWidth
        this.racketHeight = new SimpleIntegerProperty(height); // Initialize racketHeight
    }

    // Getter for racketWidth
    public int getRacketWidth() {
        return racketWidth.get();
    }

    // Property accessor for racketWidth
    public SimpleIntegerProperty widthProperty() {
        return racketWidth;
    }

    // Setter for racketWidth
    public void setRacketWidth(int racketWidth) {
        this.racketWidth.set(racketWidth);
    }

    // Getter for racketHeight
    public int getRacketHeight() {
        return racketHeight.get();
    }

    // Property accessor for racketHeight
    public SimpleIntegerProperty heightProperty() {
        return racketHeight;
    }

    // Setter for racketHeight
    public void setRacketHeight(int racketHeight) {
        this.racketHeight.set(racketHeight);
    }
}
