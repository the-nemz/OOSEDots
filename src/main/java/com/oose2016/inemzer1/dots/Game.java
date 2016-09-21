package com.oose2016.inemzer1.dots;

/**
 * Hold all of the data about a game and handles joining a game.
 * Created by Isaac on 9/18/2016.
 */
public class Game {

    public String gameId;
    public Player player1;
    public Player player2;
    public Board board;
    public State state;

    /**
     * Constructor for Game that uses the gameId from the player
     * passed to it as the gameId. Also evaluates if the player
     * passed will be player 1 or 2 based on color.
     * @param starter the game starter
     */
    public Game(Player starter) {
        this.gameId = starter.gameId;
        if (starter.playerType.equals("RED")) {
            this.player1 = starter;
        } else {
            this.player2 = starter;
        }
        this.board = new Board();
        this.state = new State();
    }

    /**
     * Function to allow the second player to join the game.
     * Determines if they will be player 1 or 2.
     * @return the new Player
     */
    public Player join() {
        if (this.player2 == null) {
            this.player2 = new Player(this.gameId, "2", "BLUE");
            this.state.startIt();
            return this.player2;
        } else if (this.player1 == null) {
            this.player1 = new Player(this.gameId, "1", "RED");
            this.state.startIt();
            return this.player1;
        }
        return null;
    }

    /**
     * A toString for testing.
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "gameId:" + this.gameId;
    }


}
