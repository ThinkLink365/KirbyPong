package com.example.oop_project_semester2.Controller;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class KeyboardListener {

    // Method to handle keyboard events for the racket movement
    public void RacketMoving(Stage stage, AtomicInteger p1RacketSpeed, AtomicInteger p2RacketSpeed) {
        // Add event handler for key pressed events
        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    p1RacketSpeed.set(-5); // Set racket speed for player 1 moving up
                    break;
                case S:
                    p1RacketSpeed.set(5); // Set racket speed for player 1 moving down
                    break;
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

    // Method to handle keyboard events for stopping racket movement
    public void RacketStop(Stage stage, AtomicInteger p1RacketSpeed, AtomicInteger p2RacketSpeed) {
        // Add event handler for key released events
        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            switch (event.getCode()) {
                case W:
                case S:
                    p1RacketSpeed.set(0); // Set racket speed for player 1 to stop
                    break;
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
