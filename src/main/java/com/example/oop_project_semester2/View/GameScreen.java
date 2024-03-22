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

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class is the main screen for the game.
 * After taking in options from the title screen, the game begins.
 * Title screen must be run first as game screen will not be able to run without taking in options from title screen
 */
public class GameScreen extends Application implements Serializable {

    private Stage window;
    private Player player1;
    private Player player2;
    private Ball ball;
    private Racket racket;
    private final BallMovement ballMovement;

    private final RacketMovement racketMovement;

    private Button restartButton;

    private Text playerScore1;

    private Text playerScore2;

    private ImageView pongball;

    private Rectangle racket1;
    private Rectangle racket2;

    private AtomicInteger p1RacketSpeed;
    private AtomicInteger p2RacketSpeed;

    private Text scoreMessage;

    private Text endMessage;

    private Scene scene;

    private boolean isPaused = false; // Track if the game is paused

    private boolean restart = false; // Track if the game needs to be restarted


    private  final String SAVE_FILE = "file.ser";



    /**
     * Instantiates a new Game screen.
     *
     * @param player1           player 1
     * @param player2           player 2
     * @param ball              the ball
     * @param racket            the racket
     * @param useSerializedData flag indicating whether to use serialized data for game state
     */
// Constructor to initialize the game screen with players, ball, and racket
    public GameScreen(Player player1, Player player2, Ball ball, Racket racket, boolean useSerializedData) {
        this.player1 = player1;
        this.player2 = player2;
        this.ball = ball;
        this.racket = racket;
        // Initialize ballMovement and racketMovement
        this.ballMovement = new BallMovement();
        this.racketMovement = new RacketMovement();
        // Load game state if useSerializedData is true
        if (useSerializedData) {
                loadGameState();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * Starts the game by initializing the game screen and setting up the game elements.
     *
     * @param primaryStage The primary stage for the game
     */
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
        pauseButton.setOnAction(e -> togglePause()); // Set action for pause button
        pauseButton.setFocusTraversable(false);// Disable focus traversal for pause button

        // Create restart button
        restartButton = new Button("Restart");
        restartButton.setOnAction(e -> restartGame()); // Set action for restart button
        restartButton.setFocusTraversable(false);// Disable focus traversal for restart button
        restartButton.setVisible(false);


        // Set up player details, score message and end message
        scoreMessage = new Text();
        scoreMessage.setFill(Color.WHITE);
        scoreMessage.setFont(Font.font("Arial", FontWeight.BOLD, 50));

        endMessage = new Text();
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

        // Create VBox for score message and buttons
        VBox detailsPane = new VBox(exitButton,pauseButton,restartButton,scoreMessage);
        detailsPane.setAlignment(Pos.CENTER);


        // BorderPane for the top section containing exit button and player details
        BorderPane topPane = new BorderPane();
        topPane.setLeft(player1Detail);
        topPane.setRight(player2Detail);
        topPane.setCenter(detailsPane);

        // Create rectangles for rackets
        racket1 = new Rectangle(racket.getRacketWidth(), racket.getRacketHeight());
        racket1.setFill(Color.HOTPINK);
        racket1.setStrokeWidth(5);
        racket1.setStroke(Color.PURPLE);

        racket2 = new Rectangle(racket.getRacketWidth(), racket.getRacketHeight());
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
        root.setCenter(endMessage); // Set the endMessage to the center
        root.getChildren().add(pongball); // Add the ball to root

        scene = new Scene(root, 800, 800);
        window.setScene(scene);
        window.show();

        // Atomic integers for racket speeds
        p1RacketSpeed = new AtomicInteger();
        p2RacketSpeed = new AtomicInteger();

        // Create keyboard listener to handle racket movement
        KeyboardListener keyboardListener = new KeyboardListener();
        keyboardListener.RacketMovingP1(window, p1RacketSpeed);
        keyboardListener.RacketMovingP2(window, p2RacketSpeed);
        keyboardListener.RacketStopP1(window, p1RacketSpeed);
        keyboardListener.RacketStopP2(window, p2RacketSpeed);

        // Start threads for racket movement and ball movement
        racketMovement.startRacketMovementThread(racket1, p1RacketSpeed, scene.getHeight(), racket.getRacketHeight(),player1,player2);
        racketMovement.startRacketMovementThread(racket2, p2RacketSpeed, scene.getHeight(), racket.getRacketHeight(),player1,player2);

        ballMovement.startBallMovementThread(scene, pongball, racket1, racket2, ball, player1, player2, playerScore1, playerScore2, scoreMessage, player1.getFinalscore(), endMessage);

    }

    /**
     * Handles the closing of the window. Displays a confirmation dialog to confirm exit.
     * If the user confirms exit, the game state is serialized for future use.
     */
    private void closeProgram() {
        // Display confirmation dialog for window closing
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Exit");
        alert.setContentText("Are you sure you want to exit?");

        // Wait for user response in the confirmation dialog
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                saveGameState(); // Serialize the game when the user exits for future use
                window.close(); // Close the window if user confirms
            }
        });
    }

    /**
     * Serializes the current game state and saves it to a file.
     * The game state includes player information, ball, and racket details.
     * In case of any error during serialization, the stack trace is printed.
     */
    private void saveGameState() {
        try {
            FileOutputStream file = new FileOutputStream(SAVE_FILE);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(player1);
            out.writeObject(player2);
            out.writeObject(ball);
            out.writeObject(racket);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the saved game state from a file.
     * Deserializes the player information, ball, and racket details.
     * In case of any error during deserialization, the stack trace is printed.
     */
    private void loadGameState() {
        File savedFile = new File(SAVE_FILE);
        if (!savedFile.exists()) {
            System.out.println("Serialized data file does not exist. No data loaded.");
            return;
        }

        try {
            FileInputStream file = new FileInputStream(SAVE_FILE);
            ObjectInputStream in = new ObjectInputStream(file);
            player1 = (Player) in.readObject();
            player2 = (Player) in.readObject();
            ball = (Ball) in.readObject();
            racket = (Racket) in.readObject();
            in.close();
            file.close();
            ball.setImage(new ImageView("file:src/Kirby.png"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Toggles the pause state of the game.
     * Pauses or resumes ball and racket movement threads accordingly.
     * Also toggles the visibility of the restart button.
     */
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

    /**
     * Restarts the game by resetting player scores, restarting the ball movement,
     * and restarting the racket movement threads.
     * The game restarts only when the restart flag is set to true.
     */
    private void restartGame() {
        restart = !restart;

        if (restart) {
            player1.setPlayerScore(0);// Set the score of player 1 to 0
            player2.setPlayerScore(0);// Set the score of player 2 to 0
            playerScore1.setText(player1.getPlayerScore() + ""); // Update the player scores to reflect the change
            playerScore2.setText(player2.getPlayerScore() + "");
            ballMovement.restartBall(pongball, ball, scene, endMessage);// Restart the ball

            // Restart ball and racket movement threads
            racketMovement.startRacketMovementThread(racket1, p1RacketSpeed, scene.getHeight(), racket.getRacketHeight(), player1, player2);
            racketMovement.startRacketMovementThread(racket2, p2RacketSpeed, scene.getHeight(), racket.getRacketHeight(), player1, player2);
            ballMovement.startBallMovementThread(scene, pongball, racket1, racket2, ball, player1, player2, playerScore1, playerScore2, scoreMessage, player1.getFinalscore(), endMessage);
        }
    }
}
