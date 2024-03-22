package com.example.oop_project_semester2.Controller;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class handles the listeners that let the rackets move independently.
 */
public class KeyboardListener {

    /**
     * Check if a key is being pressed.
     *
     * @param stage         the stage
     * @param p1RacketSpeed the racket speed of player 1
     */
// Method to handle keyboard events for the racket movement
    public void RacketMovingP1(Stage stage, AtomicInteger p1RacketSpeed) {
        // Add event handler for key pressed events
        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    p1RacketSpeed.set(-5); // Set racket speed for player 1 moving up
                    break;
                case S:
                    p1RacketSpeed.set(5); // Set racket speed for player 1 moving down
                    break;
            }
        });
    }


    /**
     * Check if a key is being pressed.
     *
     * @param stage         the stage
     * @param p2RacketSpeed the racket speed of player 2
     */

    public void RacketMovingP2(Stage stage, AtomicInteger p2RacketSpeed) {
        // Add event handler for key pressed events
        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case UP:
                    p2RacketSpeed.set(-5); // Set racket speed for player 2 moving up
                    break;
                case DOWN:
                    p2RacketSpeed.set(5); // Set racket speed for player 2 moving down
                    break;
                default:
                    break;
            }
        });
    }
    /**
     * Check if a key is released.
     *
     * @param stage         the stage
     * @param p1RacketSpeed the racket speed of player 1
     */
// Method to handle keyboard events for stopping racket movement
    public void RacketStopP1(Stage stage, AtomicInteger p1RacketSpeed) {
        // Add event handler for key released events
        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            switch (event.getCode()) {
                case W:
                case S:
                    p1RacketSpeed.set(0); // Set racket speed for player 1 to stop
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * Check if a key is released.
     *
     * @param stage         the stage
     * @param p2RacketSpeed the racket speed of player 2
     */
// Method to handle keyboard events for stopping racket movement
    public void RacketStopP2(Stage stage, AtomicInteger p2RacketSpeed) {
        // Add event handler for key released events
        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            switch (event.getCode()) {
                case UP:
                case DOWN:
                    p2RacketSpeed.set(0); // Set racket speed for player 2 to stop
                    break;
                default:
                    break;
            }
        });
    }
}
