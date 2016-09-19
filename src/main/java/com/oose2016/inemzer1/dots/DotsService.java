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

    public Game createGame(String body) {
        Game newGame = new Gson().fromJson(body, Game.class);
        if (newGame.playerType.equals("RED")) {
            newGame.playerId = "1";
        } else {
            newGame.playerId = "2";
        }
        newGame.gameId = String.valueOf(this.gameNum);
        this.gameNum++;
        this.theGames.put(newGame.gameId, newGame);
        //this.theGames.add(newGame);
        return newGame;
    }

    public Game joinGame(String gid, String body) {
        System.out.println(gid);
        System.out.println(body);
        //Game tempGame = new Gson().fromJson(body, Game.class);
        Game game = theGames.get(gid);
        //Game game = this.findGame(tempGame.gameId);
        game.join();
        System.out.println(game);
        return game;
    }

    public Board getBoard(String gid, String body) {
        System.out.println("b gid:" + gid);
        System.out.println("b body:" + body);
        //Game tempGame = new Gson().fromJson(body, Game.class);
        Game game = this.theGames.get(gid);
        //System.out.println("t:" + tempGame);
        //Game game = this.findGame(tempGame.gameId);
        System.out.println("b g:" + game);
        return game.board;
    }

    public State getState(String gid, String body) {
        System.out.println("s gid:" + gid);
        System.out.println("s body:" + body);
        //Game tempGame = new Gson().fromJson(body, Game.class);
        //System.out.println("t:" + tempGame);
        Game game = this.theGames.get(gid);
        //Game game = this.findGame(tempGame.gameId);
        System.out.println("s g:" + game);
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
