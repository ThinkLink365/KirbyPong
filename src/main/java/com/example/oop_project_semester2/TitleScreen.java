package com.example.oop_project_semester2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TitleScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root,600,600, Color.DEEPPINK);

        Image icon = new Image("file:src/Kirby.jpg");
        Image player1Icon = new Image("file:src/kirbyP1.png");
        Image player2Icon = new Image("file:src/Meta_Knight.png");

        ImageView p1 = new ImageView(player1Icon);
        p1.setX(200);
        p1.setY(150);
        p1.setFitHeight(300);
        p1.setFitWidth(300);

        ImageView p2 = new ImageView(player2Icon);
        p2.setX(1000);
        p2.setY(150);
        p2.setFitHeight(300);
        p2.setFitWidth(300);

        TextField p1Name = new TextField();
        TextField p2Name = new TextField();
        p1Name.setLayoutX(300);
        p1Name.setLayoutY(500);
        p2Name.setLayoutX(1000);
        p2Name.setLayoutY(500);

        stage.getIcons().add(icon);
        stage.setTitle("Kirby Pong");
        stage.setWidth(800);
        stage.setHeight(800);
        stage.setFullScreen(true);

        Text title = new Text();
        title.setText("Welcome to Kirby Pong");
        title.setX(300);
        title.setY(75);
        title.setFont(Font.font("Times new roman", 100));
        title.setFill(Color.BLUEVIOLET);

        Text p1text = new Text();
        p1text.setText("Enter P1");
        p1text.setX(300);
        p1text.setY(550);
        p1text.setFont(Font.font("Times new roman", 30));
        p1text.setFill(Color.BLUEVIOLET);


        Text p2text = new Text();
        p2text.setText("Enter P2");
        p2text.setX(1000);
        p2text.setY(550);
        p2text.setFont(Font.font("Times new roman", 30));
        p2text.setFill(Color.BLUEVIOLET);

        Button btn = new Button();
        btn.setText("Begin");
        btn.setLayoutX(50);
        btn.setLayoutY(50);



        root.getChildren().add(title);
        root.getChildren().add(p1);
        root.getChildren().add(p2);
        root.getChildren().add(p1Name);
        root.getChildren().add(p2Name);
        root.getChildren().add(p1text);
        root.getChildren().add(p2text);
        root.getChildren().add(btn);
        stage.setScene(scene);
        stage.show();

    }

}

