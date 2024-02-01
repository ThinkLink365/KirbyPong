package com.example.oop_project_semester2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class OOPApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root,600,600, Color.DEEPPINK);

        Image icon = new Image("file:src/Kirby.jpg");

        stage.getIcons().add(icon);
        stage.setTitle("Kirby Pong");
        stage.setWidth(800);
        stage.setHeight(800);
        stage.setFullScreen(true);


        Text text = new Text();
        text.setText("Welcome to Kirby Pong");
        text.setX(300);
        text.setY(75);
        text.setFont(Font.font("Times new roman", 100));
        text.setFill(Color.BLUEVIOLET);

        Rectangle racket1 = new Rectangle();
        racket1.setX(100);
        racket1.setY(100);
        racket1.setWidth(20);
        racket1.setHeight(700);
        racket1.setFill(Color.HOTPINK);
        racket1.setStrokeWidth(5);
        racket1.setStroke(Color.PURPLE);


        Rectangle racket2 = new Rectangle();
        racket2.setX(1200);
        racket2.setY(100);
        racket2.setWidth(20);
        racket2.setHeight(700);
        racket2.setFill(Color.HOTPINK);
        racket2.setStrokeWidth(5);
        racket2.setStroke(Color.PURPLE);


        Image image = new Image("file:src/kirby.png");
        ImageView imageView = new ImageView(image);
        imageView.setX(750);
        imageView.setY(400);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        root.getChildren().add(text);
        root.getChildren().add(racket1);
        root.getChildren().add(racket2);
        root.getChildren().add(imageView);
        stage.setScene(scene);
        stage.show();

    }
}