package com.example.oop_project_semester2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ball {
    private ImageView image;
    private int ballSpeed;

    private int speedIncrease;

    public Ball(Image image, int speed, int increase) {
        this.image = new ImageView(image);
        this.ballSpeed = speed;
        this.speedIncrease = increase;
    }

    public ImageView getImage() {
        return image;
    }

    public int getBallSpeed() {
        return ballSpeed;
    }

    public int getSpeedIncrease() {
        return speedIncrease;
    }

    public void setBallSpeed(int ballSpeed) {
        this.ballSpeed = ballSpeed;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setSpeedIncrease(int speedIncrease) {
        this.speedIncrease = speedIncrease;
    }
}

