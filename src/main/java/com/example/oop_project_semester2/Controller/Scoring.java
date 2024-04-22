    package com.example.oop_project_semester2.Controller;

    import com.example.oop_project_semester2.Model.Player;
    import javafx.animation.FadeTransition;
    import javafx.scene.text.Text;
    import javafx.util.Duration;

    /**
     * This class handles the scoring of the players.
     */
    public class Scoring {

        /**
         * Check if a player has scored and update the score.
         *
         * @param ballX        the position of teh ball on the x-axis
         * @param sceneWidth   the scene width
         * @param player1      player 1
         * @param player2      player 2
         * @param playerScore1 the score of player 1
         * @param playerScore2 the score of player 2
         * @param scoreMessage the score message
         * @param finalScore   the final score
         */
    // Method to check if a player scored and update the score accordingly
        public static void checkAndUpdateScore(double ballX, double sceneWidth, Player player1, Player player2,
                                               Text playerScore1, Text playerScore2, Text scoreMessage, int finalScore) {
            if (ballX <= -50) {
                // Player 2 scores
                player2.incrementPlayerScore(); // Increment player 2's score
                updatePlayerScoreText(playerScore2, player2.getPlayerScore()); // Update player 2's score text
                showMessage(scoreMessage, player2.getName() + " scores!"); // Show that player 2 scored

                // Check if player 2 reaches the final score
            } else if (ballX >= sceneWidth - 50) {
                // Player 1 scores
                player1.incrementPlayerScore(); // Increment player 1's score
                updatePlayerScoreText(playerScore1, player1.getPlayerScore()); // Update player 1's score text
                showMessage(scoreMessage, player1.getName() + " scores!"); // Show that player 1 scored

                // Check if player 1 reaches the final score
            }
        }


        /**
         * Update player score text.
         *
         * @param playerScoreText the player score text
         * @param score           the score
         */
    // Method to update the player's score text
        public static void updatePlayerScoreText(Text playerScoreText, int score) {
            playerScoreText.setText(Integer.toString(score)); // Update player score text
        }

        /**
         * Show which player scored.
         *
         * @param messageText the message text
         * @param message     the message
         */
    // Method to display a message temporarily
        public static void showMessage(Text messageText, String message) {
            messageText.setText(message); // Update message text

            // Fade out the message after a delay
            FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(2), messageText);
            fadeOutTransition.setFromValue(1.0);
            fadeOutTransition.setToValue(0.0);
            fadeOutTransition.play();
        }
    }
