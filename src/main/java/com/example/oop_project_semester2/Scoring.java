package com.example.oop_project_semester2;
public class Scoring {
    public static void checkAndUpdateScore(double ballX, double sceneWidth, Player player1, Player player2) {
        if (ballX <= 0) {
            // Player 2 scores
            player2.incrementPlayerScore(player2.getPlayerScore());
            System.out.println(player2.getPlayerScore());
        } else if (ballX >= sceneWidth) {
            // Player 1 scores
            player1.incrementPlayerScore(player1.getPlayerScore());
            System.out.println(player1.getPlayerScore());
        }
    }
}
