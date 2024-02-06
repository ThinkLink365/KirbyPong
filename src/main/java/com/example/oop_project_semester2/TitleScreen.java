package com.example.oop_project_semester2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;


public class TitleScreen extends Application{

    private Player player1;
    private Player player2;
    private Ball ball;

    private Racket racket;
    private Button btn;

    private Button p1btn;

    private Button p2btn;

    private Button scorebtn;

    Stage window;


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

        Image icon = new Image("file:src/Kirby.jpg");
        Image player1Icon = new Image("file:src/kirbyP1.png");
        Image player2Icon = new Image("file:src/Meta_Knight.png");

        player1 = new Player("Player 1", 0);
        player2 = new Player("Player 2", 0);
        ball = new Ball(icon, 1, 1);
        racket = new Racket(400, 20);

        AtomicInteger finalscore = new AtomicInteger();

        Group root = new Group();
        Scene scene = new Scene(root,600,600, Color.DEEPPINK);


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
        TextField setScore = new TextField();
        TextField setHeight = new TextField();
        TextField setWidth = new TextField();
        p1Name.setText(player1.getName());
        p2Name.setText(player2.getName());
        setScore.setText("Set the final score");
        setHeight.setText("Set the Height");
        setWidth.setText("Set the Width");
        p1Name.setLayoutX(300);
        p1Name.setLayoutY(500);
        p2Name.setLayoutX(1000);
        p2Name.setLayoutY(500);
        setScore.setLayoutX(600);
        setScore.setLayoutY(500);
        setHeight.setLayoutX(600);
        setWidth.setLayoutX(500);
        setWidth.setLayoutY(700);



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

        btn = new Button();
        btn.setOnAction(e  -> System.out.println("It works"));
        btn.setText("Begin");
        btn.setLayoutX(50);
        btn.setLayoutY(50);

        p1btn = new Button();
        p1btn.setText("Confirm");
        p1btn.setLayoutX(350);
        p1btn.setLayoutY(600);
        p1btn.setOnAction(e -> {
            String name = p1Name.getText();
            player1.setName(name);
        } );

        p2btn = new Button();
        p2btn.setText("Confirm");
        p2btn.setLayoutX(1050);
        p2btn.setLayoutY(600);
        p2btn.setOnAction(e -> {
            String name = p2Name.getText();
            player2.setName(name);
        } );


        scorebtn = new Button();
        scorebtn.setText("Confirm");
        scorebtn.setLayoutX(1050);
        scorebtn.setLayoutY(650);
        scorebtn.setOnAction(e -> {
            try {
                int score = Integer.parseInt(setScore.getText());
                finalscore.set(score); // Update the final score
                System.out.println("Final score set to: " + finalscore);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid score format");
            }} );


        Button heightbtn = new Button();
        heightbtn.setText("Confirm Height");
        heightbtn.setLayoutX(1050);
        heightbtn.setLayoutY(750);
        heightbtn.setOnAction(e -> {
            try {
                int height = Integer.parseInt(setHeight.getText());
                racket.setRacketHeight(height);
                System.out.println("Height set to: " + height);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid height format");
            }} );

        Button widthbtn = new Button();
        widthbtn.setText("Confirm Width");
        widthbtn.setLayoutX(1200);
        widthbtn.setLayoutY(750);
        widthbtn.setOnAction(e -> {
            try {
                int width = Integer.parseInt(setWidth.getText());
                racket.setRacketWidth(width);
                System.out.println("Width set to: " + width);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid height format");
            }} );


        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> closeProgram());


        ToggleGroup speedToggleGroup = new ToggleGroup();

        RadioButton slowSpeedRadioButton = new RadioButton("Slow");
        slowSpeedRadioButton.setToggleGroup(speedToggleGroup);
        slowSpeedRadioButton.setLayoutX(50);
        slowSpeedRadioButton.setLayoutY(200);
        slowSpeedRadioButton.setSelected(true); // Default selection

        RadioButton mediumSpeedRadioButton = new RadioButton("Medium");
        mediumSpeedRadioButton.setToggleGroup(speedToggleGroup);
        mediumSpeedRadioButton.setLayoutX(50);
        mediumSpeedRadioButton.setLayoutY(250);

        RadioButton fastSpeedRadioButton = new RadioButton("Fast");
        fastSpeedRadioButton.setToggleGroup(speedToggleGroup);
        fastSpeedRadioButton.setLayoutX(50);
        fastSpeedRadioButton.setLayoutY(300);

        speedToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (speedToggleGroup.getSelectedToggle() != null) {
                String speed = ((RadioButton) speedToggleGroup.getSelectedToggle()).getText();
                switch (speed) {
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
            }
        });

        ComboBox<String> speedIncreaseOptions = new ComboBox<>();
        speedIncreaseOptions.getItems().addAll("Every hit", "Every 2 hits", "Every 3 hits");
        speedIncreaseOptions.setValue("Every hit"); // Default value

        speedIncreaseOptions.setLayoutX(50);
        speedIncreaseOptions.setLayoutY(350);

        speedIncreaseOptions.setOnAction(e -> {
            String selectedOption = speedIncreaseOptions.getValue();
            switch (selectedOption) {
                case "Every hit":
                    ball.setSpeedIncrease(1);
                    break;
                case "Every 2 hits":
                    ball.setSpeedIncrease(2);
                    break;
                case "Every 3 hits":
                    ball.setSpeedIncrease(3);
                    break;
                default:
                    break;
            }
        });




        root.getChildren().addAll(title,p1,p2,p1Name,p2Name,p1text,p2text,btn,p1btn,p2btn,slowSpeedRadioButton,mediumSpeedRadioButton,fastSpeedRadioButton,setScore,scorebtn,exitButton,setHeight,setWidth,heightbtn,widthbtn,speedIncreaseOptions);
        window.setScene(scene);
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


}


