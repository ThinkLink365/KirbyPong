package com.example.oop_project_semester2.Controller;

import com.example.oop_project_semester2.Model.Ball;
import com.example.oop_project_semester2.Model.Player;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.concurrent.atomic.AtomicReference;

public class BallMovement {

    private boolean isPaused = false; // Variable to track pause state

    private double ballXSpeed;
    private double ballYSpeed;


    // Method to start the ball movement thread
    public void startBallMovementThread(Scene scene, ImageView pongball, Rectangle racket1, Rectangle racket2,
                                        Ball ballSpeed, Player player1, Player player2, Text player1Score,
                                        Text player2Score, Text scoreMessage, int finalScore, Text winnerMessage) {

        // Creating a new thread for ball movement
        Thread ballMovementThread = new Thread(() -> {
            ballXSpeed = ballSpeed.getBallSpeed(); // Initial speed of the ball along the x-axis
            ballYSpeed = ballSpeed.getBallSpeed(); // Initial speed of the ball along the y-axis


            //Variable created for the rotation of the ball
            final AtomicReference<Double> i = new AtomicReference<>((double) 0);
            float direction = 1;
            double speedIncreaseFactor = 1.1;
            int speedIncreaseCounter1 = 0; // Counter for tracking speed increase for every bounce
            int speedIncreaseCounter2 = 0; // Counter for tracking speed every second bounce
            int speedIncreaseCounter3 = 0; // Counter for tracking speed every third bounce

            final double MAX_SPEED = 8.0;

            // Ball movement loop
            while (true) {
                if (!isPaused) {
                    double newBallX = pongball.getX() + ballXSpeed;
                    double newBallY = pongball.getY() + ballYSpeed;

                    // Check if the ball hits the top or bottom borders, and reverse its direction if necessary
                    if (newBallY <= 100 || newBallY >= scene.getHeight() - pongball.getFitHeight()) {
                        ballYSpeed = -ballYSpeed;
                        if (ballSpeed.getSpeedIncrease() == 1) {
                            speedIncreaseCounter1++;
                            ballXSpeed *= speedIncreaseFactor;
                            ballYSpeed *= speedIncreaseFactor;
                        } else if (ballSpeed.getSpeedIncrease() == 2) {
                            speedIncreaseCounter2++;
                            if (speedIncreaseCounter2 % 2 == 0) {
                                ballXSpeed *= speedIncreaseFactor;
                                ballYSpeed *= speedIncreaseFactor;
                            }
                        } else if (ballSpeed.getSpeedIncrease() == 3) {
                            speedIncreaseCounter3++;
                            if (speedIncreaseCounter3 % 3 == 0) {
                                ballXSpeed *= speedIncreaseFactor;
                                ballYSpeed *= speedIncreaseFactor;
                            }
                        }
                        if (Math.abs(ballXSpeed) > MAX_SPEED) {
                            ballXSpeed = Math.signum(ballXSpeed) * MAX_SPEED; // Cap X speed
                        }
                        if (Math.abs(ballYSpeed) > MAX_SPEED) {
                            ballYSpeed = Math.signum(ballYSpeed) * MAX_SPEED; // Cap Y speed
                        }
                    }

                    // Handle collision with left racket
                    if (CollisionDetection.isCollidingWithRacket(pongball, racket1)) {
                        direction *= -1;
                        ballXSpeed = Math.abs(ballXSpeed); // Ensure positive X speed

                        if (ballSpeed.getSpeedIncrease() == 1) {
                            speedIncreaseCounter1++;
                            ballXSpeed *= speedIncreaseFactor;
                            ballYSpeed *= speedIncreaseFactor;
                        } else if (ballSpeed.getSpeedIncrease() == 2) {
                            speedIncreaseCounter2++;
                            if (speedIncreaseCounter2 % 2 == 0) {
                                ballXSpeed *= speedIncreaseFactor;
                                ballYSpeed *= speedIncreaseFactor;
                            }
                        } else if (ballSpeed.getSpeedIncrease() == 3) {
                            speedIncreaseCounter3++;
                            if (speedIncreaseCounter3 % 3 == 0) {
                                ballXSpeed *= speedIncreaseFactor;
                                ballYSpeed *= speedIncreaseFactor;
                            }
                        }
                        if (Math.abs(ballXSpeed) > MAX_SPEED) {
                            ballXSpeed = Math.signum(ballXSpeed) * MAX_SPEED; // Cap X speed
                        }
                        if (Math.abs(ballYSpeed) > MAX_SPEED) {
                            ballYSpeed = Math.signum(ballYSpeed) * MAX_SPEED; // Cap Y speed
                        }
                    }

                    // Handle collision with right racket
                    if (CollisionDetection.isCollidingWithRacket(pongball, racket2)) {
                        ballXSpeed = -Math.abs(ballXSpeed); // Ensure negative X speed
                        direction *= -1;
                        pongball.setRotate(i.getAndSet(i.get() - 1)); // Increment rotation angle
                        if (ballSpeed.getSpeedIncrease() == 1) {
                            speedIncreaseCounter1++;
                            ballXSpeed *= speedIncreaseFactor;
                            ballYSpeed *= speedIncreaseFactor;
                        } else if (ballSpeed.getSpeedIncrease() == 2) {
                            speedIncreaseCounter2++;
                            if (speedIncreaseCounter2 % 2 == 0) {
                                ballXSpeed *= speedIncreaseFactor;
                                ballYSpeed *= speedIncreaseFactor;
                            }
                        } else if (ballSpeed.getSpeedIncrease() == 3) {
                            speedIncreaseCounter3++;
                            if (speedIncreaseCounter3 % 3 == 0) {
                                ballXSpeed *= speedIncreaseFactor;
                                ballYSpeed *= speedIncreaseFactor;
                            }
                        }
                        if (Math.abs(ballXSpeed) > MAX_SPEED) {
                            ballXSpeed = Math.signum(ballXSpeed) * MAX_SPEED; // Cap X speed
                        }
                        if (Math.abs(ballYSpeed) > MAX_SPEED) {
                            ballYSpeed = Math.signum(ballYSpeed) * MAX_SPEED; // Cap Y speed
                        }

                    }

                    // Check if the ball hits pass the left or right borders
                    if (newBallX <= -50 || newBallX >= scene.getWidth() - 50) {
                        // Update players' scores and display a message showing who scored
                        boolean gameOver = Scoring.checkAndUpdateScore(newBallX, scene.getWidth(), player1, player2,
                                player1Score, player2Score, scoreMessage, finalScore);

                        if (gameOver) {
                            // Check if any player has reached the final score
                            if (player1.getPlayerScore() >= finalScore) {
                                // End the game
                                Platform.runLater(() -> {
                                    // Display winner message
                                    winnerMessage.setText(player1.getName() + " wins!");
                                });
                                return; // Exit the thread
                            } else if (player2.getPlayerScore() >= finalScore) {
                                // End the game
                                Platform.runLater(() -> {
                                    // Display winner message
                                    winnerMessage.setText(player2.getName() + " wins!");
                                });
                                return; // Exit the thread

                            }
                        }

                        // Reset ball position to center
                        newBallX = scene.getWidth() / 2 - pongball.getFitWidth() / 2;
                        newBallY = scene.getHeight() / 2 - pongball.getFitHeight() / 2;

                        // Apply previous speed after resetting
                        ballXSpeed = ballSpeed.getBallSpeed();
                        ballYSpeed = ballSpeed.getBallSpeed();
                    }

                    // Update ball position and rotation on the UI thread
                    final double x = newBallX;
                    final double y = newBallY;
                    double finalBallXSpeed = ballXSpeed;
                    Platform.runLater(() -> {
                        pongball.setX(x);
                        pongball.setY(y);
                        pongball.setRotate(i.getAndSet(i.get() + finalBallXSpeed / 2.0f)); // Increment rotation angle
                    });
                }

                // Pause for smoother animation
                try {
                    Thread.sleep(10);
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
            }
        });

        // Set the thread as daemon and start it
        ballMovementThread.setDaemon(true);
        ballMovementThread.start();
    }

    // Method to pause the ball movement thread
    public void pauseBallMovementThread() {
        isPaused = true;
    }

    // Method to resume the ball movement thread
    public void resumeBallMovementThread() {
        isPaused = false;
        synchronized (this) {
            notify(); // Notify the thread to resume
        }
    }

    public void restartBall(ImageView pongball, Ball ball, Scene scene) {
        // Reset ball position to center
        double newBallX = scene.getWidth() / 2 - pongball.getFitWidth() / 2;
        double newBallY = scene.getHeight() / 2 - pongball.getFitHeight() / 2;

        // Apply previous speed after resetting
        ballXSpeed = ball.getBallSpeed();
        ballYSpeed = ball.getBallSpeed();

        // Update ball position on the UI thread
        Platform.runLater(() -> {
            pongball.setX(newBallX);
            pongball.setY(newBallY);
        });
    }



}
