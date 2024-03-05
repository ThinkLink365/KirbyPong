package com.example.oop_project_semester2;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicInteger;

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
    public void moveRacket(VBox racketPane, int speed, double sceneHeight) {
        if (racketPane != null && racketPane.getScene() != null) {
            // Assuming racketPane is the VBox containing the racket
            double newY = racketPane.getTranslateY() + speed;

            // Adjust newY to prevent racket from moving out of bounds
            newY = Math.min(sceneHeight - racketHeight.get() - 100, Math.max(0, newY));

            racketPane.setTranslateY(newY);
        }
    }

    // Method to start racket movement thread
    public void startRacketMovementThread(VBox racketPane, AtomicInteger speed, double sceneHeight) {
        Thread racketMovementThread = new Thread(() -> {
            while (true) {
                Platform.runLater(() -> moveRacket(racketPane, speed.get(), sceneHeight));
                try {
                    Thread.sleep(10); // Adjust this value for desired speed
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }
            }
        });
        racketMovementThread.setDaemon(true);
        racketMovementThread.start();
    }
}
