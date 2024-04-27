/**
 * The DBGameInfo interface defines methods for saving and loading game information to/from the database.
 */
package com.example.oop_project_semester2.Controller;

import com.example.oop_project_semester2.Model.Player;

public interface DBGameInfo {
    /**
     * Saves game information for two players to the database.
     * @param player1 The first player.
     * @param player2 The second player.
     */
    void saveGameInfo(Player player1, Player player2,String gameName);

    /**
     * Loads game information for two players from the database.
     * @param player1Builder The builder for player 1.
     * @param player2Builder The builder for player 2.
     */
    void loadGameInfo(Builder player1Builder, Builder player2Builder);
}
