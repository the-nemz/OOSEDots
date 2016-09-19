package com.oose2016.inemzer1.dots;

/**
 * Created by Isaac on 9/18/2016.
 */
public class Game {

    public String gameId;
    public Player player1;
    public Player player2;
    public transient Board board;
    public transient State state;


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

    public Player join() {
        System.out.println("1");
        if (this.player2 == null) {
            System.out.println("2");
            this.player2 = new Player(this.gameId, "2", "BLUE");
            System.out.println("3");
            this.state.startIt();
            System.out.println("4");
            return this.player2;
        } else if (this.player1 == null) {
            System.out.println("5");
            this.player1 = new Player(this.gameId, "1", "RED");
            this.state.startIt();
            return this.player1;
        }
        return null;
    }

    @Override
    public String toString() {
        return "gameId:" + this.gameId;
    }


}
