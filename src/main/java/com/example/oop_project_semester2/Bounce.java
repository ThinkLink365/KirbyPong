package com.example.oop_project_semester2;

import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class Bounce {

    public static void handleBounce(VBox racketPane, ImageView ball, double sceneWidth) {
        if (detectCollison.isColliding(racketPane, ball)) {
            // Reverse ball's x-speed
            ball.setTranslateX(-ball.getTranslateX());
            System.out.println("ball reversed");

            // Ensure ball stays within scene bounds
            if (ball.getTranslateX() <= 0) {
                ball.setTranslateX(0);
            } else if (ball.getTranslateX() >= sceneWidth - ball.getFitWidth()) {
                ball.setTranslateX(sceneWidth - ball.getFitWidth());
            }
        }
    }

}
