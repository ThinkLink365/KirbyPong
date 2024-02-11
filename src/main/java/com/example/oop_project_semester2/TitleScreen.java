package com.example.oop_project_semester2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;


public class TitleScreen extends Application {

    private Player player1;
    private Player player2;
    private Ball ball;

    private Racket racket;

    private Stage window;
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

        // Title text
        Text titleText = new Text("Welcome to Kirby Pong");
        titleText.setFill(Color.WHITE);

        // Set initial font size
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 100));

        // Listener to update font size when window is resized
        window.widthProperty().addListener((obs, oldVal, newVal) -> {
            titleText.setFont(Font.font("Arial", FontWeight.BOLD, window.getWidth() / 15));
        });

        // Set the position of the title text
        StackPane.setAlignment(titleText, Pos.TOP_CENTER);

        // "Choose your options" text
        Text chooseOptionsText = new Text("Choose your options below");
        chooseOptionsText.setFill(Color.WHITE);
        chooseOptionsText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        StackPane.setAlignment(chooseOptionsText, Pos.CENTER);

        VBox titleVBox = new VBox(titleText, chooseOptionsText);
        titleVBox.setSpacing(10);
        titleVBox.setAlignment(Pos.TOP_CENTER);

        HBox options = new HBox();
        options.setSpacing(10);
        options.setAlignment(Pos.CENTER); // Changed alignment to Pos.CENTER

        Image icon = new Image("file:src/Kirby.jpg");
        Image kirbyBall = new Image("file:src/Kirby.png");
        Image kirbyRender = new Image("file:src/kirbyP1.png");
        Image metaKnightRender = new Image("file:src/Meta_Knight.png");
        Image dededeRender = new Image("file:src/dedede.png");

        // Initialize player1 and player2 with default values
        player1 = new Player("Player 1", 0);
        player2 = new Player("Player 2", 0);

        ball = new Ball(kirbyBall, 1, 1);
        racket = new Racket(20, 400);

        finalscore = new AtomicInteger();

        Scene titleScene = new Scene(menu, 800, 800, Color.DEEPPINK);



        ImageView image1 = new ImageView(kirbyRender);
        image1.setFitHeight(300);
        image1.setPreserveRatio(true); // Preserve aspect ratio
        image1.fitWidthProperty().bind(window.widthProperty().multiply(0.2)); // Set width dynamically

        // Set the position of player 1 ImageView to the left center of the StackPane
        StackPane.setAlignment(image1, Pos.BOTTOM_CENTER);


        ImageView image2 = new ImageView(metaKnightRender);
        image2.setFitHeight(300);
        image2.setPreserveRatio(true); // Preserve aspect ratio
        image2.fitWidthProperty().bind(window.widthProperty().multiply(0.2)); // Set width dynamically

        // Set the position of player 1 ImageView to the left center of the StackPane
        StackPane.setAlignment(image2, Pos.CENTER_LEFT);

        ImageView image3 = new ImageView(dededeRender);
        image3.setFitHeight(300);
        image3.setPreserveRatio(true); // Preserve aspect ratio
        image3.fitWidthProperty().bind(window.widthProperty().multiply(0.2)); // Set width dynamically

        // Set the position of player 1 ImageView to the left center of the StackPane
        StackPane.setAlignment(image3, Pos.CENTER_RIGHT);


        window.getIcons().add(icon);
        window.setTitle("Kirby Pong");
        window.setFullScreen(true);

        VBox textFieldGroup = createTextFields();options.getChildren().addAll(textFieldGroup);
        menu.getChildren().addAll(options,titleVBox,image1,image2,image3);
        options.toFront(); // Bring options to the front
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

        Button switchButton = new Button("Begin");
        switchButton.setOnAction(e -> {
            window.hide();
            // Pass instances of Player, Ball, and Racket to GameScreen constructor
            new GameScreen(player1, player2, ball, racket).start(new Stage());
        });
        // Exit button
        Button exitButton1 = new Button("Exit");
        exitButton1.setOnAction(e -> closeProgram());

        HBox gameHBox = new HBox();
        gameHBox.setSpacing(140); // Set spacing between combo boxes
        gameHBox.setAlignment(Pos.CENTER_LEFT);

        gameHBox.getChildren().addAll(switchButton,exitButton1);


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
}
