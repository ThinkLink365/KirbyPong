package com.example.oop_project_semester2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameScreen extends Application {

    private Stage window;
    private final Player player1;
    private final Player player2;
    private final Ball ball;
    private final Racket racket;

    public GameScreen(Player player1, Player player2, Ball ball, Racket racket) {
        this.player1 = player1;
        this.player2 = player2;
        this.ball = ball;
        this.racket = racket;
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        Image icon = new Image("file:src/Kirby.jpg");
        window.getIcons().add(icon);
        window.setTitle("Kirby Pong");
        window.setFullScreen(true);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> closeProgram());

        Text playerName1 = new Text(player1.getName());
        playerName1.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        playerName1.setFill(Color.WHITE);
        Text playerScore1 = new Text(player1.getPlayerScore() + "");
        playerScore1.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        playerScore1.setFill(Color.WHITE);

        Text playerName2 = new Text(player2.getName());
        playerName2.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        playerName2.setFill(Color.WHITE);
        Text playerScore2 = new Text(player2.getPlayerScore() + "");
        playerScore2.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        playerScore2.setFill(Color.WHITE);

        // Create VBox for player 1 details
        VBox player1Detail = new VBox(playerName1, playerScore1);
        player1Detail.setAlignment(Pos.TOP_LEFT); // Align to top left

        // Create VBox for player 2 details
        VBox player2Detail = new VBox(playerName2, playerScore2);
        player2Detail.setAlignment(Pos.TOP_RIGHT); // Align to top right

        // BorderPane for the top section containing exit button and player details
        BorderPane topPane = new BorderPane();
        topPane.setStyle("-fx-background-color: deeppink;");
        topPane.setLeft(new StackPane(player1Detail));
        topPane.setRight(new StackPane(player2Detail));
        topPane.setCenter(exitButton);
        BorderPane.setAlignment(exitButton, Pos.TOP_CENTER);

        Rectangle racket1 = new Rectangle(racket.getRacketWidth(), racket.getRacketHeight());
        racket1.setFill(Color.HOTPINK);
        racket1.setStrokeWidth(5);
        racket1.setStroke(Color.PURPLE);

        Rectangle racket2 = new Rectangle(racket.getRacketWidth(), racket.getRacketHeight());
        racket2.setFill(Color.HOTPINK);
        racket2.setStrokeWidth(5);
        racket2.setStroke(Color.PURPLE);

        // VBox to center the racket1 (left racket)
        VBox leftRacketPane = new VBox();
        leftRacketPane.setAlignment(Pos.CENTER_LEFT);
        leftRacketPane.getChildren().add(racket1);

        // VBox to center the racket2 (right racket)
        VBox rightRacketPane = new VBox();
        rightRacketPane.setAlignment(Pos.CENTER_RIGHT);
        rightRacketPane.getChildren().add(racket2);

        ImageView pongball = new ImageView(ball.getImage().getImage());
        pongball.setX(750);
        pongball.setY(400);
        pongball.setFitHeight(100);
        pongball.setFitWidth(100);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: deeppink;");
        root.setCenter(new StackPane(pongball)); // Center ball
        root.setLeft(leftRacketPane); // Set left racket
        root.setRight(rightRacketPane); // Set right racket
        root.setTop(topPane); // Set the top section BorderPane

        Scene scene = new Scene(root, 800, 800);
        window.setScene(scene);

        window.show();
    }

    public static void main(String[] args) {
        launch(args);
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
}
