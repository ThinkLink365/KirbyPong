package com.example.oop_project_semester2.Controller;

import com.example.oop_project_semester2.Model.Player;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.concurrent.atomic.AtomicReference;

public class BallMovement {

    // Method to start the ball movement thread
    public void startBallMovementThread(Scene scene, ImageView pongball, Rectangle racket1, Rectangle racket2,
                                        int ballSpeed, Player player1, Player player2, Text player1Score,
                                        Text player2Score, Text scoreMessage) {

        // Creating a new thread for ball movement
        Thread ballMovementThread = new Thread(() -> {
            double ballXSpeed = 1; // Initial speed of the ball along the x-axis
            double ballYSpeed = 1; // Initial speed of the ball along the y-axis

            // Adjusting ball speed based on user selection
            if (ballSpeed == 2) {
                ballXSpeed = 2;
                ballYSpeed = 2;
            } else if (ballSpeed == 3) {
                ballXSpeed = 3;
                ballYSpeed = 3;
            }
            //Variable created for the rotation of the ball
            final AtomicReference<Double> i = new AtomicReference<>((double) 0);

            // Ball movement loop
            while (true) {
                double newBallX = pongball.getX() + ballXSpeed;
                double newBallY = pongball.getY() + ballYSpeed;

                // Check if the ball hits the top or bottom borders, and reverse its direction if necessary
                if (newBallY <= 100 || newBallY >= scene.getHeight() - pongball.getFitHeight()) {
                    ballYSpeed = -ballYSpeed;
                }

                // Handle collision with left racket
                if (CollisionDetection.isCollidingWithRacket(pongball, racket1)) {
                    ballXSpeed = Math.abs(ballXSpeed); // Ensure positive X speed
                }

                // Handle collision with right racket
                if (CollisionDetection.isCollidingWithRacket(pongball, racket2)) {
                    ballXSpeed = -Math.abs(ballXSpeed); // Ensure negative X speed
                }

                // Check if the ball hits left or right borders
                if (newBallX <= -50 || newBallX >= scene.getWidth() - 50) {
                    // Store current speed before resetting
                    double oldBallXSpeed = ballXSpeed;
                    double oldBallYSpeed = ballYSpeed;

                    // Update players' scores and display a message showing who scored
                    Scoring.checkAndUpdateScore(newBallX, scene.getWidth(), player1, player2, player1Score,
                            player2Score, scoreMessage);

                    // Reset ball position to center
                    newBallX = scene.getWidth() / 2 - pongball.getFitWidth() / 2;
                    newBallY = scene.getHeight() / 2 - pongball.getFitHeight() / 2;

                    // Apply previous speed after resetting
                    ballXSpeed = oldBallXSpeed;
                    ballYSpeed = oldBallYSpeed;
                }

                // Update ball position and rotation on the UI thread
                final double x = newBallX;
                final double y = newBallY;
                Platform.runLater(() -> {
                    pongball.setX(x);
                    pongball.setY(y);

                    pongball.setRotate(i.getAndSet(i.get() + 1)); // Increment rotation angle
                });

                try {
                    Thread.sleep(10); // Pause for smoother animation
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }
            }
        });

        // Set the thread as daemon and start it
        ballMovementThread.setDaemon(true);
        ballMovementThread.start();
        // Listener for scene width changes
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            // Adjust ball X position proportionally to the new scene width
            double ratioX = newValue.doubleValue() / oldValue.doubleValue();
            pongball.setX(pongball.getX() * ratioX);
        });

        // Listener for scene height changes
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            // Adjust ball Y position proportionally to the new scene height
            double ratioY = newValue.doubleValue() / oldValue.doubleValue();
            pongball.setY(pongball.getY() * ratioY);
        });

        // Listener for scene resize to force ball rendering update
        scene.setOnMouseClicked(event -> {
            pongball.setImage(pongball.getImage()); // Force refresh the ball rendering
        });
    }
}
