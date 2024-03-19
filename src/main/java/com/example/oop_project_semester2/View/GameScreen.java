package com.example.oop_project_semester2.View;

import com.example.oop_project_semester2.Controller.BallMovement;
import com.example.oop_project_semester2.Controller.KeyboardListener;
import com.example.oop_project_semester2.Controller.RacketMovement;
import com.example.oop_project_semester2.Model.Ball;
import com.example.oop_project_semester2.Model.Player;
import com.example.oop_project_semester2.Model.Racket;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class GameScreen extends Application {

    private Stage window;
    private final Player player1;
    private final Player player2;
    private final Ball ball;
    private final Racket racket;
    private final BallMovement ballMovement;

    private final RacketMovement racketMovement;

    private Button restartButton;

    private Text playerScore1;

    private Text playerScore2;

    private ImageView pongball;

    private Scene scene;

    private boolean isPaused = false; // Track if the game is paused

    private boolean restart = false;

    // Constructor to initialize the game screen with players, ball, and racket
    public GameScreen(Player player1, Player player2, Ball ball, Racket racket) {
        this.player1 = player1;
        this.player2 = player2;
        this.ball = ball;
        this.racket = racket;
        this.ballMovement = new BallMovement();
        this.racketMovement = new RacketMovement();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set up the primary stage and handle window closing event
        window = primaryStage;
        window.setOnCloseRequest(e -> {
            e.consume(); // Consume the event to prevent default window close behavior
            closeProgram(); // Call method to handle window closing
        });

        // Set window properties and icon
        Image icon = new Image("file:src/Kirby.jpg");
        window.getIcons().add(icon);
        window.setTitle("Kirby Pong");
        window.setMinHeight(800);
        window.setMinWidth(800);
        window.setFullScreen(true);

        // Create exit button
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> closeProgram()); // Set action for exit button
        exitButton.setFocusTraversable(false); // Disable focus traversal for exit button

        // Create pause button
        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(e -> togglePause());
        pauseButton.setFocusTraversable(false);// Disable focus traversal for pause button

        // Create restart button
        restartButton = new Button("Restart");
        restartButton.setOnAction(e -> restartGame());
        restartButton.setFocusTraversable(false);
        restartButton.setVisible(false);


        // Set up player details and score message
        Text scoreMessage = new Text();
        scoreMessage.setFill(Color.WHITE);
        scoreMessage.setFont(Font.font("Arial", FontWeight.BOLD, 50));

        Text endMessage = new Text();
        endMessage.setFill(Color.WHITE);
        endMessage.setFont(Font.font("Arial", FontWeight.BOLD, 50));

        // Player 1 details
        Text playerName1 = new Text(player1.getName());
        playerName1.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        playerName1.setFill(Color.WHITE);
        playerScore1 = new Text(player1.getPlayerScore() + "");
        playerScore1.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        playerScore1.setFill(Color.WHITE);

        // Player 2 details
        Text playerName2 = new Text(player2.getName());
        playerName2.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        playerName2.setFill(Color.WHITE);
        playerScore2 = new Text(player2.getPlayerScore() + "");
        playerScore2.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        playerScore2.setFill(Color.WHITE);

        // Create VBox for player 1 details
        VBox player1Detail = new VBox(playerName1, playerScore1);
        player1Detail.setAlignment(Pos.TOP_LEFT);

        // Create VBox for player 2 details
        VBox player2Detail = new VBox(playerName2, playerScore2);
        player2Detail.setAlignment(Pos.TOP_RIGHT);

        // Create VBox for score message and button
        VBox detailsPane = new VBox(exitButton,pauseButton,restartButton,scoreMessage);
        detailsPane.setAlignment(Pos.CENTER);


        // BorderPane for the top section containing exit button and player details
        BorderPane topPane = new BorderPane();
        topPane.setLeft(player1Detail);
        topPane.setRight(player2Detail);
        topPane.setCenter(detailsPane);

        // Create rectangles for rackets
        Rectangle racket1 = new Rectangle(racket.getRacketWidth(), racket.getRacketHeight());
        racket1.setFill(Color.HOTPINK);
        racket1.setStrokeWidth(5);
        racket1.setStroke(Color.PURPLE);

        Rectangle racket2 = new Rectangle(racket.getRacketWidth(), racket.getRacketHeight());
        racket2.setFill(Color.HOTPINK);
        racket2.setStrokeWidth(5);
        racket2.setStroke(Color.PURPLE);

        // Set up ball
        pongball = ball.getImage();
        pongball.setX(750);
        pongball.setY(400);
        pongball.setFitHeight(100);
        pongball.setFitWidth(100);

        // BorderPane for the game layout
        BorderPane root = new BorderPane();
        BorderPane.setAlignment(pongball, Pos.CENTER); // Center the Ball
        root.setStyle("-fx-background-color: deeppink;"); //Set the background colour
        root.setLeft(racket1); // Set left racket
        root.setRight(racket2); // Set right racket
        root.setTop(topPane); // Set the top section BorderPane
        root.setCenter(endMessage);
        root.getChildren().add(pongball); // Add the ball to root

        scene = new Scene(root, 800, 800);
        window.setScene(scene);

        window.show();

        // Atomic integers for racket speeds
        AtomicInteger p1RacketSpeed = new AtomicInteger();
        AtomicInteger p2RacketSpeed = new AtomicInteger();

        // Create keyboard listener to handle racket movement
        KeyboardListener keyboardListener = new KeyboardListener();
        keyboardListener.RacketMoving(window, p1RacketSpeed, p2RacketSpeed);
        keyboardListener.RacketStop(window, p1RacketSpeed, p2RacketSpeed);

        // Start threads for racket movement and ball movement
        racketMovement.startRacketMovementThread(racket1, p1RacketSpeed, scene.getHeight(), racket.getRacketHeight());
        racketMovement.startRacketMovementThread(racket2, p2RacketSpeed, scene.getHeight(), racket.getRacketHeight());

        ballMovement.startBallMovementThread(scene, pongball, racket1, racket2, ball, player1, player2, playerScore1, playerScore2, scoreMessage, player1.getFinalscore(), endMessage);

    }

    // Method to handle closing of the window
    private void closeProgram() {
        // Display confirmation dialog for window closing
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Exit");
        alert.setContentText("Are you sure you want to exit?");

        // Wait for user response in the confirmation dialog
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                window.close(); // Close the window if user confirms
            }
        });
    }
    private void togglePause() {
        isPaused = !isPaused; // Toggle pause state

        // If game is paused, stop ball movement thread
        if (isPaused) {
            ballMovement.pauseBallMovementThread();
            racketMovement.pauseRacketMovementThread();
            racketMovement.pauseRacketMovementThread();
            restartButton.setVisible(true); // Show restart button
        } else { // If game is resumed, start ball movement thread
            ballMovement.resumeBallMovementThread();
            racketMovement.resumeRacketMovementThread();
            racketMovement.resumeRacketMovementThread();
            restartButton.setVisible(false); // Hide restart button
        }

}

    private void restartGame(){
        restart = !restart;

        if (restart){
            player1.setPlayerScore(0);
            player2.setPlayerScore(0);
            playerScore1.setText(player1.getPlayerScore() + "");
            playerScore2.setText(player2.getPlayerScore() + "");
            ballMovement.restartBall(pongball,ball,scene);
        }
    }
}
