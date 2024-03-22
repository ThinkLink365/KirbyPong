package com.example.oop_project_semester2.Controller;

import com.example.oop_project_semester2.Model.Player;
import javafx.application.Platform;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class handles the movement of the racket.
 */
public class RacketMovement {

    private boolean isPaused = false; // Variable to track pause state

    /**
     * Move the racket at a given speed.
     *
     * @param racket       the racket
     * @param speed        the speed the racket moves
     * @param sceneHeight  the scene height
     * @param racketHeight the racket height
     */
// Method to move the racket based on the provided speed
    public void moveRacket(Rectangle racket, int speed, double sceneHeight, int racketHeight) {
        if (racket != null && racket.getScene() != null) {
            double newY = racket.getTranslateY() + speed;

            // Ensure the racket stays within the set boundaries
            newY = Math.min(sceneHeight - racketHeight - 135, Math.max(0, newY));

            // Set the new position of the racket
            racket.setTranslateY(newY);
        }
    }

    /**
     * Start racket movement thread.
     *
     * @param racket       the racket
     * @param speed        the speed the racket moves
     * @param sceneHeight  the scene height
     * @param racketHeight the racket height
     * @param player1      player 1
     * @param player2      player 2
     */
// Method to start a thread for continuous racket movement
    public void startRacketMovementThread(Rectangle racket, AtomicInteger speed, double sceneHeight, int racketHeight, Player player1, Player player2) {
        Thread racketMovementThread = new Thread(() -> {
            // Continuous loop for updating racket position
            while (true) {
                // If the game is not paused then run the thread, otherwise stop it
                if (!isPaused) {
                    // Update racket position on the UI thread
                    Platform.runLater(() -> moveRacket(racket, speed.get(), sceneHeight, racketHeight));
                }

                try {
                    Thread.sleep(10); // Adjust this value for desired speed
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }

                // Check if the game is paused
                synchronized (this) {
                    while (isPaused) {
                        try {
                            wait(); // Wait until resumed
                        } catch (InterruptedException e) {
                            System.out.println("Thread interrupted: " + e.getMessage());
                        }
                    }
                }

                // Check if the game has ended
                if (isGameEnded(player1,player2)) {
                    return; // Exit the loop if the game has ended
                }
            }
        });

        // Set the thread as daemon and start it
        racketMovementThread.setDaemon(true);
        racketMovementThread.start();
    }

    /**
     * Check if the game has ended
     *
     * @param player1 player 1
     * @param player2 player 2
     *
     * @return true if game over, false otherwise
     */

    // Method to check if the game has ended
    private boolean isGameEnded(Player player1, Player player2) {
        return player1.getPlayerScore() >= player1.getFinalscore()|| player2.getPlayerScore() >= player2.getFinalscore();
    }

    /**
     * Pause racket movement thread.
     */
// Method to pause the racket movement thread
    public void pauseRacketMovementThread() {
        isPaused = true;
    }

    /**
     * Resume racket movement thread.
     */
// Method to resume the racket movement thread
    public void resumeRacketMovementThread() {
        isPaused = false;
        synchronized (this) {
            notify(); // Notify the thread to resume
        }
    }
}
