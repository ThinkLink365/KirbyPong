package com.example.oop_project_semester2;

import javafx.application.Platform;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicInteger;

public class RacketMovement {
    public static void moveRacket(VBox racketPane, int speed, double sceneHeight, int racketHeight) {
        if (racketPane != null && racketPane.getScene() != null) {
            double newY = racketPane.getTranslateY() + speed;
            newY = Math.min(sceneHeight - racketHeight - 100, Math.max(0, newY));
            racketPane.setTranslateY(newY);
        }
    }

    public static void startRacketMovementThread(VBox racketPane, AtomicInteger speed, double sceneHeight, int racketHeight) {
        Thread racketMovementThread = new Thread(() -> {
            while (true) {
                Platform.runLater(() -> moveRacket(racketPane, speed.get(), sceneHeight, racketHeight));
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
