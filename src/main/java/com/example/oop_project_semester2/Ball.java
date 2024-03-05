package com.example.oop_project_semester2;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Ball {
    // Attributes
    private ImageView image; // ImageView to display the ball
    private int ballSpeed; // Speed of the ball
    private int speedIncrease; // Speed increase factor

    // Constructor
    public Ball(Image image, int speed, int increase) {
        this.image = new ImageView(image); // Initialize the image with provided Image
        this.ballSpeed = speed; // Set the initial ball speed
        this.speedIncrease = increase; // Set the speed increase factor
    }

    // Getter for the image
    public ImageView getImage() {
        return image;
    }

    // Getter for the ball speed
    public int getBallSpeed() {
        return ballSpeed;
    }

    // Getter for the speed increase factor
    public int getSpeedIncrease() {
        return speedIncrease;
    }

    // Setter for the ball speed
    public void setBallSpeed(int ballSpeed) {
        this.ballSpeed = ballSpeed;
    }

    // Setter for the image
    public void setImage(ImageView image) {
        this.image = image;
    }

    // Setter for the speed increase factor
    public void setSpeedIncrease(int speedIncrease) {
        this.speedIncrease = speedIncrease;
    }

    public void startBallMovementThread(Scene scene, ImageView pongball, VBox leftRacketPane, VBox rightRacketPane) {
        Thread ballMovementThread = new Thread(() -> {
            final AtomicInteger[] ballXSpeed = {new AtomicInteger(1)}; // Initial speed of the ball along the x-axis
            final AtomicInteger[] ballYSpeed = {new AtomicInteger(1)}; // Initial speed of the ball along the y-axis

            double ballXPos = scene.getWidth() / 2;
            double ballYPos = scene.getWidth() / 2;

            if (ballSpeed == 2) {
                ballXSpeed[0].set(2);
                ballYSpeed[0].set(2);
            } else if (ballSpeed == 3) {
                ballXSpeed[0].set(3);
                ballYSpeed[0].set(3);
            }

            final AtomicReference<Double>[] i = new AtomicReference[]{new AtomicReference<>((double) 0)};

            final boolean[] leftCollision = {false};
            final boolean[] rightCollision = {false};

            while (true) {
                Platform.runLater(() -> {
                    double newBallX = pongball.getX() + ballXSpeed[0].get();
                    double newBallY = pongball.getY() + ballYSpeed[0].get();

                    // Check if the ball hits the top or bottom borders, and reverse its direction if necessary
                    if (newBallY <= 100 || newBallY >= scene.getHeight() - pongball.getFitHeight()) {
                        ballYSpeed[0].set(-ballYSpeed[0].get());
                    }

                    // Handle collision with left racket
                    if (newBallX + pongball.getFitWidth() >= leftRacketPane.getBoundsInParent().getMinX() &&
                            newBallX <= leftRacketPane.getBoundsInParent().getMaxX() &&
                            newBallY + pongball.getFitHeight() >= leftRacketPane.getBoundsInParent().getMinY() &&
                            newBallY <= leftRacketPane.getBoundsInParent().getMaxY() && !leftCollision[0]) {
                        ballXSpeed[0].set(-ballXSpeed[0].get()); // Reverse the ball's X velocity
                        leftCollision[0] = true;
                        rightCollision[0] = false;
                    } else {
                        leftCollision[0] = false;
                    }

                    // Handle collision with right racket
                    if (newBallX <= rightRacketPane.getBoundsInParent().getMaxX() &&
                            newBallX + pongball.getFitWidth() >= rightRacketPane.getBoundsInParent().getMinX() &&
                            newBallY + pongball.getFitHeight() >= rightRacketPane.getBoundsInParent().getMinY() &&
                            newBallY <= rightRacketPane.getBoundsInParent().getMaxY() && !rightCollision[0]) {
                        ballXSpeed[0].set(-ballXSpeed[0].get()); // Reverse the ball's X velocity
                        rightCollision[0] = true;
                        leftCollision[0] = false;
                    } else {
                        rightCollision[0] = false;
                    }

                    pongball.setX(newBallX);
                    pongball.setY(newBallY);

                    pongball.setRotate(i[0].getAndSet(i[0].get() + 1));

                    // Print positions
                    System.out.println("Ball X Position: " + newBallX);
                    System.out.println("Ball Y Position: " + newBallY);
                    System.out.println("Left Racket X Position: " + leftRacketPane.getBoundsInParent().getMinX());
                    System.out.println("Left Racket Y Position: " + leftRacketPane.getBoundsInParent().getMinY());
                    System.out.println("Right Racket X Position: " + rightRacketPane.getBoundsInParent().getMinX());
                    System.out.println("Right Racket Y Position: " + rightRacketPane.getBoundsInParent().getMinY());
                });

                try {
                    Thread.sleep(10); // Adjust this value for desired speed
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
