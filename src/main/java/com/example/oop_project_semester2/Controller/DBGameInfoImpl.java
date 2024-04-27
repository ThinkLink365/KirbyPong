/**
 * The DBGameInfoImpl class implements the DBGameInfo interface to save and load game information to/from the database.
 */
package com.example.oop_project_semester2.Controller;

import com.example.oop_project_semester2.Model.Player;

import java.sql.*;

public class DBGameInfoImpl implements DBGameInfo {

    /**
     * Saves game information for two players to the database.
     * @param player1 The first player.
     * @param player2 The second player.
     */
    @Override
    public void saveGameInfo(Player player1, Player player2, String gameName) {
        // Get the singleton instance of DatabaseManager
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // Get the connection
        Connection connection = dbManager.getConnection();

        try {
            // Disable autocommit
            connection.setAutoCommit(false);

            // Select the database
            String useDatabaseQuery = "USE kirby_pong";
            Statement useDatabaseStatement = connection.createStatement();
            useDatabaseStatement.execute(useDatabaseQuery);
            useDatabaseStatement.close();

            // Insert player data for player1
            String insertPlayer1Query = "INSERT INTO gameInfo (game_name,player_name, score, finalscore) VALUES (?, ?, ?, ?)";
            PreparedStatement player1Statement = connection.prepareStatement(insertPlayer1Query);
            player1Statement.setString(1, gameName);
            player1Statement.setString(2, player1.getName());
            player1Statement.setInt(3, player1.getPlayerScore());
            player1Statement.setInt(4, player1.getFinalscore());
            player1Statement.executeUpdate();
            player1Statement.close();

            // Insert player data for player2
            String insertPlayer2Query = "INSERT INTO gameInfo (game_name,player_name, score, finalscore) VALUES (?, ?, ?, ?)";
            PreparedStatement player2Statement = connection.prepareStatement(insertPlayer2Query);
            player2Statement.setString(1, gameName);
            player2Statement.setString(2, player2.getName());
            player2Statement.setInt(3, player2.getPlayerScore());
            player2Statement.setInt(4, player2.getFinalscore());
            player2Statement.executeUpdate();
            player2Statement.close();

            // Commit the transaction
            connection.commit();

            System.out.println("Game data saved to database successfully.");

        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
            try {
                // Rollback the transaction in case of exception
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                // Re-enable autocommit
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // Close the connection
            dbManager.close();
        }
    }

    /**
     * Loads game information for two players from the database.
     * @param player1Builder The builder for player 1.
     * @param player2Builder The builder for player 2.
     */
    @Override
    public void loadGameInfo(Builder player1Builder, Builder player2Builder) {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        Connection connection = dbManager.getConnection();

        try {
            String useDatabaseQuery = "USE kirby_pong";
            Statement useDatabaseStatement = connection.createStatement();
            useDatabaseStatement.execute(useDatabaseQuery);
            useDatabaseStatement.close();

            String latestGameQueryPlayer1 = "SELECT * FROM gameInfo ORDER BY player_id DESC LIMIT 2";
            Statement latestGameStatementPlayer1 = connection.createStatement();
            ResultSet resultSetPlayer1 = latestGameStatementPlayer1.executeQuery(latestGameQueryPlayer1);

            // Fetch data for player 1
            while (resultSetPlayer1.next()) {
                String playerName = resultSetPlayer1.getString("player_name");
                int score = resultSetPlayer1.getInt("score");
                int finalScore = resultSetPlayer1.getInt("finalscore");

                player1Builder.setName(playerName)
                        .setPlayerScore(score)
                        .setFinalScore(finalScore);
            }

            String latestGameQueryPlayer2 = "SELECT * FROM gameInfo ORDER BY player_id DESC LIMIT 1";
            Statement latestGameStatementPlayer2 = connection.createStatement();
            ResultSet resultSetPlayer2 = latestGameStatementPlayer2.executeQuery(latestGameQueryPlayer2);

            // Fetch data for player 2
            while (resultSetPlayer2.next()) {
                String playerName = resultSetPlayer2.getString("player_name");
                int score = resultSetPlayer2.getInt("score");
                int finalScore = resultSetPlayer2.getInt("finalscore");

                player2Builder.setName(playerName)
                        .setPlayerScore(score)
                        .setFinalScore(finalScore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
