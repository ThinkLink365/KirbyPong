package com.example.oop_project_semester2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.property.SimpleIntegerProperty;


import java.util.concurrent.atomic.AtomicInteger;


public class TitleScreen extends Application {

    private Player player1;
    private Player player2;
    private Ball ball;

    private Racket racket;

    private Stage window;

    private Scene titleScene, mainScene;

    private Text p1name;

    private Text p2name;

    private TextField p1Name;
    private TextField p2Name;
    private TextField setScore;
    private TextField setHeight;
    private TextField setWidth;

    private AtomicInteger finalscore;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage PrimaryStage) {
        window = PrimaryStage;
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        StackPane menu = new StackPane();
        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-background-color: deeppink;");

        HBox options = new HBox();
        options.setSpacing(10);
        options.setAlignment(Pos.CENTER);

        Image icon = new Image("file:src/Kirby.jpg");
        Image kirbyBall = new Image("file:src/Kirby.png");
        Image player1Icon = new Image("file:src/kirbyP1.png");
        Image player2Icon = new Image("file:src/Meta_Knight.png");

        // Initialize player1 and player2 with default values
        player1 = new Player("Player 1", 0);
        player2 = new Player("Player 2", 0);

        ball = new Ball(kirbyBall, 1, 1);
        racket = new Racket(20, 400);

        finalscore = new AtomicInteger();

        Group titleScreen = new Group();
        Group main = new Group();

        titleScene = new Scene(menu, 1200, 800, Color.DEEPPINK);

        mainScene = new Scene(main, 800, 1200, Color.DEEPPINK);
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

        window.getIcons().add(icon);
        window.setTitle("Kirby Pong");
        window.setWidth(800);
        window.setHeight(800);
        window.setFullScreen(true);

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

        VBox textFieldGroup = createTextFields();
        //main scene items

        Button exitButton2 = new Button("Exit");
        exitButton2.setOnAction(e -> closeProgram());


        p1name = new Text(player1.getName());
        p1name.setX(100);
        p1name.setY(100);


        p2name = new Text(player2.getName());
        p2name.setX(500);
        p2name.setY(100);

        Text p1score = new Text(player1.getPlayerScore() + "");
        p1score.setX(100);
        p1score.setY(150);


        Text p2score = new Text(player2.getPlayerScore() + "");
        p2score.setX(500);
        p2score.setY(150);

        ImageView pongball = new ImageView(ball.getImage().getImage());
        pongball.setX(750);
        pongball.setY(400);
        pongball.setFitHeight(100);
        pongball.setFitWidth(100);

        Rectangle racket1 = new Rectangle();
        racket1.setX(100);
        racket1.setY(100);
        racket1.setWidth(racket.getRacketWidth());
        racket1.setHeight(racket.getRacketHeight());
        racket1.setFill(Color.HOTPINK);
        racket1.setStrokeWidth(5);
        racket1.setStroke(Color.PURPLE);

        Rectangle racket2 = new Rectangle();
        racket2.setX(1200);
        racket2.setY(100);
        racket2.setWidth(racket.getRacketWidth());
        racket2.setHeight(racket.getRacketHeight());
        racket2.setFill(Color.HOTPINK);
        racket2.setStrokeWidth(5);
        racket2.setStroke(Color.PURPLE);

        racket1.widthProperty().bind(racket.widthProperty());
        racket1.heightProperty().bind(racket.heightProperty());

        racket2.widthProperty().bind(racket.widthProperty());
        racket2.heightProperty().bind(racket.heightProperty());

        options.getChildren().addAll(textFieldGroup);
        menu.getChildren().addAll(options);
        main.getChildren().addAll(exitButton2, p1name, p2name, p1score, p2score, pongball, racket1, racket2);
        window.setScene(titleScene);
        window.show();
    }

    private void closeProgram() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Exit");
        alert.setContentText("Are you sure you want to exit?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                window.close();
            }
        });
    }
    private VBox createTextFields() {
        VBox textFieldGroup = new VBox();
        textFieldGroup.setSpacing(10);
        textFieldGroup.setAlignment(Pos.CENTER_LEFT); // Changed alignment to Pos.CENTER_LEFT

        HBox player1Box = new HBox();
        player1Box.setSpacing(5);
        p1Name = new TextField(player1.getName());
        p1Name.setPromptText("Enter P1");
        Button p1btn = new Button("Confirm P1");
        p1btn.setAlignment(Pos.CENTER);
        p1btn.setOnAction(e -> {
            String name = p1Name.getText();
            player1.setName(name);
            p1name.setText(name);
        });
        player1Box.getChildren().addAll(p1Name, p1btn);

        HBox player2Box = new HBox();
        player2Box.setSpacing(5);
        p2Name = new TextField(player2.getName());
        p2Name.setPromptText("Enter P2");
        Button p2btn = new Button("Confirm P2");
        p2btn.setAlignment(Pos.CENTER);
        p2btn.setOnAction(e -> {
            String name = p2Name.getText();
            player2.setName(name);
            p2name.setText(name);
        });
        player2Box.getChildren().addAll(p2Name, p2btn);

        HBox setScoreBox = new HBox();
        setScoreBox.setSpacing(5);
        setScore = new TextField("Set the final score");
        Button scorebtn = new Button("Confirm score");
        scorebtn.setAlignment(Pos.CENTER);
        scorebtn.setOnAction(e -> {
            try {
                int score = Integer.parseInt(setScore.getText());
                finalscore.set(score); // Update the final score
                System.out.println("Final score set to: " + finalscore);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid score format");
            }
        });
        setScoreBox.getChildren().addAll(setScore, scorebtn);

        HBox setHeightBox = new HBox();
        setHeightBox.setSpacing(5);
        setHeight = new TextField("Set the Height");
        Button heightbtn = new Button("Confirm Height");
        heightbtn.setAlignment(Pos.CENTER);
        heightbtn.setOnAction(e -> {
            try {
                int height = Integer.parseInt(setHeight.getText());
                racket.setRacketHeight(height);
                System.out.println("Height set to: " + height);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid height format");
            }
        });
        setHeightBox.getChildren().addAll(setHeight, heightbtn);

        HBox setWidthBox = new HBox();
        setWidthBox.setSpacing(5);
        setWidth = new TextField("Set the Width");
        Button widthbtn = new Button("Confirm Width");
        widthbtn.setAlignment(Pos.CENTER);
        widthbtn.setOnAction(e -> {
            try {
                int width = Integer.parseInt(setWidth.getText());
                racket.setRacketWidth(width);
                System.out.println("Width set to: " + width);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid height format");
            }
        });
        setWidthBox.getChildren().addAll(setWidth, widthbtn);

        // Add all the HBoxes to the textFieldGroup
        textFieldGroup.getChildren().addAll(player1Box, player2Box, setScoreBox, setHeightBox, setWidthBox);

        // Create HBox to hold combo boxes
        HBox optionsHBox = new HBox();
        optionsHBox.setSpacing(10); // Set spacing between combo boxes
        optionsHBox.setAlignment(Pos.CENTER_LEFT);

        // Add combo boxes to optionsHBox
        optionsHBox.getChildren().addAll(createSpeedComboBox(), createSpeedIncreaseComboBox());

        // Add optionsHBox (containing combo boxes) under textFieldGroup
        textFieldGroup.getChildren().add(optionsHBox);

        // Begin button
        Button beginButton = new Button("Begin");
        beginButton.setOnAction(e -> window.setScene(mainScene));

        // Exit button
        Button exitButton1 = new Button("Exit");
        exitButton1.setOnAction(e -> closeProgram());

        HBox gameHBox = new HBox();
        gameHBox.setSpacing(140); // Set spacing between combo boxes
        gameHBox.setAlignment(Pos.CENTER_LEFT);

        gameHBox.getChildren().addAll(beginButton,exitButton1);


        // Add buttons to the textFieldGroup
        textFieldGroup.getChildren().add(gameHBox);

        return textFieldGroup;
    }


    private VBox createSpeedComboBox() {
        ComboBox<String> speedComboBox = new ComboBox<>();
        speedComboBox.getItems().addAll("Slow", "Medium", "Fast");
        speedComboBox.setValue("Slow"); // Default value
        speedComboBox.setOnAction(e -> {
            String selectedSpeed = speedComboBox.getValue();
            switch (selectedSpeed) {
                case "Slow":
                    ball.setBallSpeed(1);
                    break;
                case "Medium":
                    ball.setBallSpeed(2);
                    break;
                case "Fast":
                    ball.setBallSpeed(3);
                    break;
                default:
                    break;
            }
        });
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(new Label("Select Speed:"), speedComboBox);
        return vbox;
    }

    private VBox createSpeedIncreaseComboBox() {
        ComboBox<String> speedIncreaseOptions = new ComboBox<>();
        speedIncreaseOptions.getItems().addAll("Every point", "Every 2 points", "Every 3 points");
        speedIncreaseOptions.setValue("Every point"); // Default value
        speedIncreaseOptions.setOnAction(e -> {
            String selectedOption = speedIncreaseOptions.getValue();
            switch (selectedOption) {
                case "Every point":
                    ball.setSpeedIncrease(1);
                    break;
                case "Every 2 points":
                    ball.setSpeedIncrease(2);
                    break;
                case "Every 3 points":
                    ball.setSpeedIncrease(3);
                    break;
                default:
                    break;
            }
        });
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(new Label("Speed Increase Options:"), speedIncreaseOptions);
        return vbox;
    }



    private Group createOptions() {
        Group optionsGroup = new Group();

        ComboBox<String> speedComboBox = new ComboBox<>();
        speedComboBox.getItems().addAll("Slow", "Medium", "Fast");
        speedComboBox.setValue("Slow"); // Default value

        ComboBox<String> speedIncreaseOptions = new ComboBox<>();
        speedIncreaseOptions.getItems().addAll("Every point", "Every 2 points", "Every 3 points");
        speedIncreaseOptions.setValue("Every point"); // Default value

        speedComboBox.setOnAction(e -> {
            String selectedSpeed = speedComboBox.getValue();
            switch (selectedSpeed) {
                case "Slow":
                    ball.setBallSpeed(1);
                    break;
                case "Medium":
                    ball.setBallSpeed(2);
                    break;
                case "Fast":
                    ball.setBallSpeed(3);
                    break;
                default:
                    break;
            }
        });

        speedIncreaseOptions.setOnAction(e -> {
            String selectedOption = speedIncreaseOptions.getValue();
            switch (selectedOption) {
                case "Every point":
                    ball.setSpeedIncrease(1);
                    break;
                case "Every 2 points":
                    ball.setSpeedIncrease(2);
                    break;
                case "Every 3 points":
                    ball.setSpeedIncrease(3);
                    break;
                default:
                    break;
            }
        });

        VBox vbox = new VBox(10); // Vertical box to hold combo boxes
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(speedComboBox, speedIncreaseOptions);

        optionsGroup.getChildren().add(vbox);

        return optionsGroup;
    }
}
