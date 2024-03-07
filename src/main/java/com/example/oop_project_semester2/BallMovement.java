package com.example.oop_project_semester2;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicReference;

public class BallMovement {
    public void startBallMovementThread(Scene scene, ImageView pongball, VBox leftRacketPane, VBox rightRacketPane, int ballSpeed, Player player1, Player player2) {
        Thread ballMovementThread = new Thread(() -> {
            double ballXSpeed = 1; // Initial speed of the ball along the x-axis
            double ballYSpeed = 1; // Initial speed of the ball along the y-axis

            if (ballSpeed == 2) {
                ballXSpeed = 2;
                ballYSpeed = 2;
            } else if (ballSpeed == 3) {
                ballXSpeed = 3;
                ballYSpeed = 3;
            }

            final AtomicReference<Double> i = new AtomicReference<>((double) 0);

            while (true) {
                double newBallX = pongball.getX() + ballXSpeed;
                double newBallY = pongball.getY() + ballYSpeed;

                // Check if the ball hits the top or bottom borders, and reverse its direction if necessary
                if (newBallY <= 100 || newBallY >= scene.getHeight() - pongball.getFitHeight()) {
                    ballYSpeed = -ballYSpeed;
                }

                // Handle collision with left racket
                if (CollisionDetection.checkCollisionWithLeftRacket(pongball, leftRacketPane)) {
                    ballXSpeed = Math.abs(ballXSpeed); // Ensure positive X speed
                }

                // Handle collision with right racket
                if (CollisionDetection.checkCollisionWithRightRacket(pongball, rightRacketPane)) {
                    ballXSpeed = -Math.abs(ballXSpeed); // Ensure negative X speed
                }
                if (newBallX <= 0 || newBallX >= scene.getWidth()) {
                    // Store current speed before resetting
                    double oldBallXSpeed = ballXSpeed;
                    double oldBallYSpeed = ballYSpeed;
                    Scoring.checkAndUpdateScore(newBallX, scene.getWidth(), player1, player2);

                    // Reset ball position to center
                    newBallX = scene.getWidth() / 2 - pongball.getFitWidth() / 2;
                    newBallY = scene.getHeight() / 2 - pongball.getFitHeight() / 2;
                    // Apply previous speed after resetting
                    ballXSpeed = oldBallXSpeed;
                    ballYSpeed = oldBallYSpeed;
                }

                final double x = newBallX;
                final double y = newBallY;


                Platform.runLater(() -> {
                    pongball.setX(x);
                    pongball.setY(y);

                    pongball.setRotate(i.getAndSet(i.get() + 1));
                });

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }
            }
        });
        ballMovementThread.setDaemon(true);
        ballMovementThread.start();
        // Listener for scene width and height changes
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            pongball.setX(pongball.getX() * (newValue.doubleValue() / oldValue.doubleValue()));
        });
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            pongball.setY(pongball.getY() * (newValue.doubleValue() / oldValue.doubleValue()));
        });
    }
    }
