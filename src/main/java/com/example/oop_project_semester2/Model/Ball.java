package com.example.oop_project_semester2.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

/**
 * The type Ball.
 */
public class Ball implements Serializable {
    // Attributes
    private  transient ImageView image; // ImageView to display the ball
    private int ballSpeed; // Speed of the ball
    private int speedIncrease; // Speed increase factor

    /**
     * Instantiates a new Ball.
     *
     * @param image    the image
     * @param speed    the speed
     * @param increase the rate of speed increase
     */
// Constructor
    public Ball(Image image, int speed, int increase) {
        this.image = new ImageView(image); // Initialize the image with provided Image
        this.ballSpeed = speed; // Set the initial ball speed
        this.speedIncrease = increase; // Set the speed increase factor
    }

    /**
     * Gets image.
     *
     * @return the image
     */
// Getter for the image
    public ImageView getImage() {
        return image;
    }

    /**
     * Gets ball speed.
     *
     * @return the ball speed
     */
// Getter for the ball speed
    public int getBallSpeed() {
        return ballSpeed;
    }

    /**
     * Gets speed increase.
     *
     * @return the speed increase
     */
// Getter for the speed increase factor
    public int getSpeedIncrease() {
        return speedIncrease;
    }

    /**
     * Sets ball speed.
     *
     * @param ballSpeed the ball speed
     */
// Setter for the ball speed
    public void setBallSpeed(int ballSpeed) {
        this.ballSpeed = ballSpeed;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
// Setter for the image
    public void setImage(ImageView image) {
        this.image = image;
    }

    /**
     * Sets speed increase.
     *
     * @param speedIncrease the speed increase
     */
// Setter for the speed increase factor
    public void setSpeedIncrease(int speedIncrease) {
        this.speedIncrease = speedIncrease;
    }
}
