package com.example.oop_project_semester2.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
}
