package com.example.oop_project_semester2.Controller;

import com.example.oop_project_semester2.Model.Ball;
import com.example.oop_project_semester2.Model.Player;
import com.example.oop_project_semester2.Model.Racket;
import com.example.oop_project_semester2.View.GameScreen;

import java.io.*;

/**
 * The GameStateSerializer class is responsible for serializing and deserializing
 * the game state, including player information, ball state, and racket state.
 * It provides methods to save the current game state to a file and load a saved
 * game state from a file.
 */
public class GameStateSerializer {

    private static final String SAVE_FILE = "KirbyPong.ser"; // File name for saving game state
    private static final GameStateSerializer instance = new GameStateSerializer(); // Singleton instance

    /**
     * Private constructor to prevent external instantiation.
     */
    private GameStateSerializer() {
        // Private constructor to prevent instantiation
    }

    /**
     * Returns the singleton instance of the GameStateSerializer.
     *
     * @return the singleton instance of the GameStateSerializer
     */
    public static GameStateSerializer getInstance() {
        return instance;
    }

    /**
     * Saves the current game state to a file.
     *
     * @param player1 the first player
     * @param player2 the second player
     * @param ball    the ball object
     * @param racket  the racket object
     */
    public void saveGameState(Player player1, Player player2, Ball ball, Racket racket) {
        try (FileOutputStream file = new FileOutputStream(SAVE_FILE);
             ObjectOutputStream out = new ObjectOutputStream(file)) {

            // Write player1, player2, ball, and racket objects to the output stream
            out.writeObject(player1);
            out.writeObject(player2);
            out.writeObject(ball);
            out.writeObject(racket);

            System.out.println("Game state saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving game state: " + e.getMessage());
        }
    }

    /**
     * Loads a saved game state from a file and returns a GameScreen object
     * representing the loaded game state.
     *
     * @return a GameScreen object representing the loaded game state, or null if loading fails
     */
    public GameScreen loadGameState() {
        File savedFile = new File(SAVE_FILE);
        if (!savedFile.exists()) {
            System.out.println("Serialized data file does not exist. No data loaded.");
            return null;
        }

        try (FileInputStream file = new FileInputStream(SAVE_FILE);
             ObjectInputStream in = new ObjectInputStream(file)) {

            // Read player1, player2, ball, and racket objects from the input stream
            Player player1 = (Player) in.readObject();
            Player player2 = (Player) in.readObject();
            Ball ball = (Ball) in.readObject();
            Racket racket = (Racket) in.readObject();

            System.out.println("Game state loaded successfully.");
            return new GameScreen(player1, player2, ball, racket); // Return a new GameScreen object
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game state: " + e.getMessage());
            return null;
        }
    }
}
