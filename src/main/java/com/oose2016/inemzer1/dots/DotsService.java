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
        //Game tempGame = new Gson().fromJson(body, Game.class);
        Game game = theGames.get(gid);
        //Game game = this.findGame(tempGame.gameId);
        Player joiner = game.join();
        return joiner;
    }

    public Board getBoard(String gid) {
        Game game = this.theGames.get(gid);
        //Game game = this.findGame(tempGame.gameId);
        //System.out.println(game.board);
        //System.out.println(new Gson().toJson(game.board));
        return game.board;
    }

    public State getState(String gid) {
        Game game = this.theGames.get(gid);
        //Game game = this.findGame(tempGame.gameId);
        //System.out.println(game.state);
        //System.out.println(new Gson().toJson(game.state));
        return game.state;
    }

    public int horizontalMove(String gid, String body) {
        Move move = new Gson().fromJson(body, Move.class);
        Game game = this.theGames.get(gid);

        //If the playerId is invalid
        if (!move.playerId.equals("1") && !move.playerId.equals("2")) {
            System.out.println("Invalid playerId: " + move.playerId);
            return 404;
        }

        //If it is not the correct player's turn
        if (!(move.playerId.equals("1") && game.state.whoseTurn.equals("RED")) &&
                !(move.playerId.equals("2") && game.state.whoseTurn.equals("BLUE"))) {
            System.out.println("Wrong turn " + move.playerId + " " + game.state.whoseTurn);
            return 422;
        }

        if (game.board.moveHorizontal(move)) {
            //valid move
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
            } else {
                game.state.nextTurn();
            }
            return 200;
        } else {
            //invalid move
            System.out.println("invalid move");
            return 422;
        }
    }

    public int verticalMove(String gid, String body) {
        Move move = new Gson().fromJson(body, Move.class);
        Game game = this.theGames.get(gid);

        //If the playerId is invalid
        if (!move.playerId.equals("1") && !move.playerId.equals("2")) {
            System.out.println("Invalid playerId: " + move.playerId);
            return 404;
        }

        //If it is not the correct player's turn
        if (!(move.playerId.equals("1") && game.state.whoseTurn.equals("RED")) &&
                !(move.playerId.equals("2") && game.state.whoseTurn.equals("BLUE"))) {
            System.out.println("Wrong turn" + move.playerId + " " + game.state.whoseTurn);
            return 422;
        }

        if (game.board.moveVertical(move)) {
            //valid move
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
            } else {
                game.state.nextTurn();
            }
            return 200;
        } else {
            //invalid move
            System.out.println("invalid move");
            return 422;
        }
    }

}
