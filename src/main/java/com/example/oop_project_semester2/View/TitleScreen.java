package com.example.oop_project_semester2.View;

import com.example.oop_project_semester2.Model.Ball;
import com.example.oop_project_semester2.Model.Player;
import com.example.oop_project_semester2.Model.Racket;
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

/**
 * TitleScreen class represents the title screen of the game.
 * It creates the user interface for the title screen and allows players to select options.
 */
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

    private ToggleButton toggleSerializationButton;

    /**
     * The main method to launch the application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes and starts the title screen of the game.
     *
     * @param primaryStage the primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        // Initialize images
        Image icon = new Image("file:src/Kirby.jpg");
        Image kirbyBall = new Image("file:src/Kirby.png");
        Image kirbyRender = new Image("file:src/kirbyP1.png");
        Image metaKnightRender = new Image("file:src/Meta_Knight.png");
        Image dededeRender = new Image("file:src/dedede.png");

        // Set up the primary stage
        window = primaryStage;
        window.getIcons().add(icon);
        window.setTitle("Kirby Pong");
        window.setMinHeight(700);
        window.setMinWidth(700);
        window.setFullScreen(true);
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        // Create a StackPane for the menu options
        StackPane menu = new StackPane();
        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-background-color: deeppink;");

        // Title text
        Text titleText = new Text("Welcome to Kirby Pong");
        titleText.setFill(Color.WHITE);
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

        // Create a VBox to line up the title text and options text underneath each other
        VBox titleVBox = new VBox(titleText, chooseOptionsText);
        titleVBox.setSpacing(10);
        titleVBox.setAlignment(Pos.TOP_CENTER);

        // Create an HBox for the options and center them
        HBox options = new HBox();
        options.setSpacing(10);
        options.setAlignment(Pos.CENTER);

        // Initialize player1, player2, ball, and racket with default values
        player1 = new Player("Player 1", 0, 5);
        player2 = new Player("Player 2", 0, 5);
        ball = new Ball(kirbyBall, 1, 1);
        racket = new Racket(20, 400);

        // Create the title scene
        Scene titleScene = new Scene(menu, 800, 800, Color.DEEPPINK);

        // Adding images
        ImageView image1 = new ImageView(kirbyRender);
        image1.setFitHeight(300);
        image1.setPreserveRatio(true);
        image1.fitWidthProperty().bind(window.widthProperty().multiply(0.2));
        StackPane.setAlignment(image1, Pos.BOTTOM_CENTER);

        ImageView image2 = new ImageView(metaKnightRender);
        image2.setFitHeight(300);
        image2.setPreserveRatio(true);
        image2.fitWidthProperty().bind(window.widthProperty().multiply(0.2));
        StackPane.setAlignment(image2, Pos.CENTER_LEFT);

        ImageView image3 = new ImageView(dededeRender);
        image3.setFitHeight(300);
        image3.setPreserveRatio(true);
        image3.fitWidthProperty().bind(window.widthProperty().multiply(0.2));
        StackPane.setAlignment(image3, Pos.CENTER_RIGHT);

        // Create a VBox for the game options
        VBox gameOptions = createGameOptions();

        // Add the game options VBox to the options HBox
        options.getChildren().addAll(gameOptions);
        menu.getChildren().addAll(options, titleVBox, image1, image2, image3);
        options.toFront();
        window.setScene(titleScene);
        window.show();
    }

    /**
     * Closes the program.
     */
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
    /**
     * Creates a VBox containing various game options such as player names, final score, racket dimensions,
     * serialization toggle, and buttons to start the game or exit.
     *
     * @return The VBox containing all game options.
     */
    private VBox createGameOptions() {
        // Create a VBox to hold all the game options
        VBox optionsSelection = new VBox();
        optionsSelection.setSpacing(10);
        optionsSelection.setAlignment(Pos.CENTER); // Align options to the center

        toggleSerializationButton = new ToggleButton("Use Serialized Data");
        toggleSerializationButton.setSelected(false); // Default: Use serialized data
        toggleSerializationButton.setOnAction(e -> {
            // Toggle between using serialized data and not using it
            toggleSerializationButton.isSelected();
        });

        // Create an HBox for entering Player 1 name
        HBox player1Box = new HBox();
        player1Box.setSpacing(5);
        p1Name = new TextField(player1.getName());
        p1Name.setPromptText("Enter P1");
        Button p1btn = new Button("Confirm P1");
        p1btn.setAlignment(Pos.CENTER);
        // Action to set Player 1 name when button is clicked
        p1btn.setOnAction(e -> {
            String name = p1Name.getText();
            player1.setName(name);
            // Check if the name is longer than 10 characters
            if(player1.getName().length() > 10){
                player1.setName("Player 1"); // Default value
            }
        });
        player1Box.getChildren().addAll(p1Name, p1btn);

        // Create an HBox for entering Player 2 name
        HBox player2Box = new HBox();
        player2Box.setSpacing(5);
        p2Name = new TextField(player2.getName());
        p2Name.setPromptText("Enter P2");
        Button p2btn = new Button("Confirm P2");
        p2btn.setAlignment(Pos.CENTER);
        // Action to set Player 2 name when button is clicked
        p2btn.setOnAction(e -> {
            String name = p2Name.getText();
            player2.setName(name);
            // Check if the name is longer than 10 characters
            if(player2.getName().length() > 10){
                player2.setName("Player 2"); // Default value
            }
        });
        player2Box.getChildren().addAll(p2Name, p2btn);

        // Create an HBox for setting the final score
        HBox setScoreBox = new HBox();
        setScoreBox.setSpacing(5);
        setScore = new TextField("Set the final score");
        Button scorebtn = new Button("Confirm score");
        scorebtn.setAlignment(Pos.CENTER);
        // Action to set the final score when button is clicked
        scorebtn.setOnAction(e -> {
            try {
                int score = Integer.parseInt(setScore.getText());
                player1.setFinalscore(score); // Update the final score
                player2.setFinalscore(score); // Update the final score
                // Check that the player score is greater than 0
                if (player1.getFinalscore() < 1 || player2.getFinalscore() < 1){
                    player1.setFinalscore(5); // Default value
                    player2.setFinalscore(5); // Default value
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid score format");
            }
        });
        setScoreBox.getChildren().addAll(setScore, scorebtn);

        // Create an HBox for setting the racket height
        HBox setHeightBox = new HBox();
        setHeightBox.setSpacing(5);
        setHeight = new TextField("Set the Height");
        Button heightbtn = new Button("Confirm Height");
        heightbtn.setAlignment(Pos.CENTER);
        // Action to set the racket height when button is clicked
        heightbtn.setOnAction(e -> {
            try {
                int height = Integer.parseInt(setHeight.getText());
                racket.setRacketHeight(height);
                // Check if the racket height is within the limit
                if (height >= 500 || height <= 100){
                    racket.setRacketHeight(400); // Default value
                }

            } catch (NumberFormatException ex) {
                System.out.println("Invalid height format");
            }
        });
        setHeightBox.getChildren().addAll(setHeight, heightbtn);

        // Create an HBox for setting the racket width
        HBox setWidthBox = new HBox();
        setWidthBox.setSpacing(5);
        setWidth = new TextField("Set the Width");
        Button widthbtn = new Button("Confirm Width");
        widthbtn.setAlignment(Pos.CENTER);
        // Action to set the racket width when button is clicked
        widthbtn.setOnAction(e -> {
            try {
                int width = Integer.parseInt(setWidth.getText());
                racket.setRacketWidth(width);
                // Check if the racket width is within the limit
                if (racket.getRacketWidth() >=30 || racket.getRacketWidth() <= 10){
                    racket.setRacketWidth(20); // Default value
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid height format");
            }
        });
        setWidthBox.getChildren().addAll(setWidth, widthbtn);

        // Add all the HBoxes to the VBox
        optionsSelection.getChildren().addAll(toggleSerializationButton,player1Box, player2Box, setScoreBox, setHeightBox, setWidthBox);

        // Create an HBox to hold combo boxes for additional game options
        HBox optionsHBox = new HBox();
        optionsHBox.setSpacing(10); // Set spacing between combo boxes

        // Add combo boxes for speed and speed increase options
        optionsHBox.getChildren().addAll(createSpeedComboBox(), createSpeedIncreaseComboBox());

        // Add the HBox with combo boxes under the VBox
        optionsSelection.getChildren().add(optionsHBox);

        // Create buttons for starting the game and exiting
        Button switchButton = new Button("Begin");
        switchButton.setOnAction(e -> {
            window.hide();
            // Pass instances of Player, Ball, and Racket to GameScreen constructor
            new GameScreen(player1, player2, ball, racket,toggleSerializationButton.isSelected()).start(new Stage());
        });
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> closeProgram());

        // Create an HBox to hold the game start and exit buttons
        HBox gameHBox = new HBox();
        gameHBox.setSpacing(140); // Set spacing between buttons
        gameHBox.getChildren().addAll(switchButton, exitButton);

        // Add the HBox with buttons to the VBox
        optionsSelection.getChildren().add(gameHBox);

        return optionsSelection; // Return the VBox containing all game options
    }

    /**
     * Creates a VBox containing a dropdown menu for selecting the speed of the ball.
     * This method initializes a ComboBox with options for "Slow", "Medium", and "Fast".
     * The default speed is set to "Slow". Upon selection of a speed option, the corresponding
     * speed value is set to the associated ball object.
     *
     * @return The VBox containing the speed selection dropdown menu.
     */
    private VBox createSpeedComboBox() {
        //Create a dropdown menu for selecting the speed of the ball
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

    /**
     * Creates a VBox containing a dropdown menu for selecting the speed increase options.
     * This method initializes a ComboBox with options for increasing speed after "Every Bounce",
     * "Every 2 Bounces", and "Every 3 Bounces". The default option is set to "Every Bounce".
     * Upon selection of a speed increase option, the corresponding value is set to the associated
     * ball object.
     *
     * @return The VBox containing the speed increase selection dropdown menu.
     */

    private VBox createSpeedIncreaseComboBox() {
        //Create a dropdown menu for selecting the speed increase options
        ComboBox<String> speedIncreaseOptions = new ComboBox<>();
        speedIncreaseOptions.getItems().addAll("Every Bounce", "Every 2 Bounces", "Every 3 Bounces");
        speedIncreaseOptions.setValue("Every Bounce"); // Default value
        speedIncreaseOptions.setOnAction(e -> {
            String selectedOption = speedIncreaseOptions.getValue();
            switch (selectedOption) {
                case "Every Bounce":
                    ball.setSpeedIncrease(1);
                    break;
                case "Every 2 Bounces":
                    ball.setSpeedIncrease(2);
                    break;
                case "Every 3 Bounces":
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
