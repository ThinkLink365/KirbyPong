package com.example.oop_project_semester2;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class detectCollison {
    public static boolean isColliding(Pane racketPane, ImageView ball) {
//        System.out.println("colliding");
        return racketPane.getBoundsInParent().intersects(ball.getBoundsInParent());
    }

    }
