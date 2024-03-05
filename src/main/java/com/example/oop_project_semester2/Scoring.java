package com.example.oop_project_semester2;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Scoring {
        private Rectangle goalArea1;
        private Rectangle goalArea2;
        private Player player1;
        private Player player2;

        public Scoring(Rectangle goalArea1, Rectangle goalArea2, Player player1, Player player2) {
            this.goalArea1 = goalArea1;
            this.goalArea2 = goalArea2;
            this.player1 = player1;
            this.player2 = player2;
        }

        public void detectGoals(ImageView ball) {
            if (goalArea1.getBoundsInParent().intersects(ball.getBoundsInParent())) {
                player2.setPlayerScore(player2.getPlayerScore() +1) ; // Increment player2's score
                // Update UI to reflect the score change
            }
            if (goalArea2.getBoundsInParent().intersects(ball.getBoundsInParent())) {
                player1.setPlayerScore(player1.getPlayerScore() +1); // Increment player1's score
                // Update UI to reflect the score change
            }
        }

}
