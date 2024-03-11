package com.example.oop_project_semester2.Controller;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import java.util.concurrent.atomic.AtomicInteger;

public class RacketMovement {

    // Method to move the racket based on the provided speed
    public static void moveRacket(Rectangle racket, int speed, double sceneHeight, int racketHeight) {
        if (racket != null && racket.getScene() != null) {
            double newY = racket.getTranslateY() + speed;

            // Ensure the racket stays within the set boundaries
            newY = Math.min(sceneHeight - racketHeight - 100, Math.max(0, newY));

            // Set the new position of the racket
            racket.setTranslateY(newY);
        }
    }

    // Method to start a thread for continuous racket movement
    public static void startRacketMovementThread(Rectangle racket, AtomicInteger speed, double sceneHeight, int racketHeight) {
        Thread racketMovementThread = new Thread(() -> {
            // Continuous loop for updating racket position
            while (true) {
                // Update racket position on the UI thread
                Platform.runLater(() -> moveRacket(racket, speed.get(), sceneHeight, racketHeight));

                try {
                    Thread.sleep(10); // Adjust this value for desired speed
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }
            }
        });

        // Set the thread as daemon and start it
        racketMovementThread.setDaemon(true);
        racketMovementThread.start();
    }
}
