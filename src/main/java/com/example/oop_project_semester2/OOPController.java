package com.example.oop_project_semester2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OOPController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}