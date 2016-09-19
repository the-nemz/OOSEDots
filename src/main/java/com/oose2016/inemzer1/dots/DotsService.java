package com.oose2016.inemzer1.dots;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.ArrayList;


/**
 * Created by Isaac on 9/18/2016.
 */
public class DotsService {

    public HashMap<String, Game> theGames;
    //public ArrayList<Game> theGames;
    public int gameNum;

    /**
     * DotsService constructor
     */
    public DotsService() {
        this.theGames = new HashMap<String, Game>();
        //this.theGames = new ArrayList<Game>();
        this.gameNum = 0;
    }

    public Game findGame(String id) {
/*        for (int a = 0; a < this.theGames.size(); a++) {
            if (id.equals(this.theGames.get(a).gameId)) {
                System.out.println("found");
                return this.theGames.get(a);
            }
        }
        System.out.println("not");*/
        return null;
    }

    public Player createGame(String body) {
        Player starter = new Gson().fromJson(body, Player.class);
        if (starter.playerType.equals("RED")) {
            starter.playerId = "1";
        } else {
            starter.playerId = "2";
        }
        starter.gameId = String.valueOf(this.gameNum);
        this.gameNum++;
        Game newGame = new Game(starter);
        this.theGames.put(newGame.gameId, newGame);
        //this.theGames.add(newGame);
        return starter;
    }

    public Player joinGame(String gid, String body) {
        System.out.println(gid);
        System.out.println(body);
        //Game tempGame = new Gson().fromJson(body, Game.class);
        Game game = theGames.get(gid);
        System.out.println("here");
        //Game game = this.findGame(tempGame.gameId);
        Player joiner = game.join();
        System.out.println(joiner);
        return joiner;
    }

    public Board getBoard(String gid) {
        System.out.println("b gid:" + gid);
        Game game = this.theGames.get(gid);
        //Game game = this.findGame(tempGame.gameId);
        //System.out.println(game.board);
        //System.out.println(new Gson().toJson(game.board));
        return game.board;
    }

    public State getState(String gid) {
        System.out.println("s gid:" + gid);
        Game game = this.theGames.get(gid);
        //Game game = this.findGame(tempGame.gameId);
        System.out.println("s g:" + game);
        //System.out.println(game.state);
        //System.out.println(new Gson().toJson(game.state));
        return game.state;
    }

    public void horizontalMove(String gid, String body) {
        Move move = new Gson().fromJson(body, Move.class);
        Game game = this.theGames.get(gid);
        if (game.board.moveHorizontal(move)) {
            int scores = game.board.checkHoriScore(move);
            if (scores > 0) {
                if (move.playerId.equals("1")) {
                    game.state.scoreForRed(scores);
                } else {
                    game.state.scoreForBlue(scores);
                }
                if (game.state.state == "FINISHED") {
                    //do something
                    System.out.println("Game over!");
                }
            }
        }
    }

    public void verticalMove(String gid, String body) {
        Move move = new Gson().fromJson(body, Move.class);
        Game game = this.theGames.get(gid);
        if (game.board.moveVertical(move)) {
            int scores = game.board.checkVertScore(move);
            if (scores > 0) {
                if (move.playerId.equals("1")) {
                    game.state.scoreForRed(scores);
                } else {
                    game.state.scoreForBlue(scores);
                }
                if (game.state.state == "FINISHED") {
                    //do something
                    System.out.println("Game over!");
                }
            }
        }
    }

}
