package com.oose2016.inemzer1.dots;

/**
 * Created by Isaac on 9/18/2016.
 */
public class Game {

    public String gameId;
    public String playerId;
    public String playerType;

    public transient Board board;
    public transient State state;


    public Game(String gameId, String playerId, String playerType) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.playerType = playerType;
        this.board = new Board();
        this.state = new State();
    }

    public void join() {
        if (this.playerId.equals("1")) {
            this.playerId = "2";
            this.playerType = "BLUE";
        } else {
            this.playerId = "1";
            this.playerType = "RED";
        }
    }

    @Override
    public String toString() {
        return "gameId:" + (String)this.gameId + " playerId:" + (String)this.playerId +
                " playerType:" + (String)this.playerType;
    }


}
