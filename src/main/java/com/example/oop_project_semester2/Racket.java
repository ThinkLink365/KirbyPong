package com.example.oop_project_semester2;

import javafx.beans.property.SimpleIntegerProperty;

public class Racket {
    private final SimpleIntegerProperty racketWidth;
    private final SimpleIntegerProperty racketHeight;

    public Racket(int width, int height) {
        this.racketWidth = new SimpleIntegerProperty(width);
        this.racketHeight = new SimpleIntegerProperty(height);
    }

    // Getters and setters for racketWidth and racketHeight
    public int getRacketWidth() {
        return racketWidth.get();
    }

    public SimpleIntegerProperty widthProperty() {
        return racketWidth;
    }

    public void setRacketWidth(int racketWidth) {
        this.racketWidth.set(racketWidth);
    }

    public int getRacketHeight() {
        return racketHeight.get();
    }

    public SimpleIntegerProperty heightProperty() {
        return racketHeight;
    }

    public void setRacketHeight(int racketHeight) {
        this.racketHeight.set(racketHeight);
    }
}
