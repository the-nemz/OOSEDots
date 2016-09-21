package com.oose2016.inemzer1.dots;

import com.google.gson.Gson;
import java.util.HashMap;


/**
 * Used to perform and call all actions routed to the backend.
 * Created by Isaac on 9/18/2016.
 */
public class DotsService {

    public HashMap<String, Game> theGames;
    public int gameNum;

    /**
     * DotsService constructor. Initializes the Hashmap of games
     * and the gameNum which will be the games' ids.
     */
    public DotsService() {
        this.theGames = new HashMap<String, Game>();
        this.gameNum = 0;
    }

    /**
     * Frist creates a player which is then used to create a game.
     * @param body The request body
     * @return the player that is created
     */
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
        return starter;
    }

    /**
     * Allows a second player to join the game.
     * @param gid the gameId
     * @return the new player
     * @throws IDInvalidException if bad game ID
     * @throws GameFullException if two players are already in the game
     */
    public Player joinGame(String gid)
            throws IDInvalidException, GameFullException {
        Game game = this.theGames.get(gid);
        if (game == null) {
            throw new IDInvalidException();
        }
        Player joiner = game.join();
        if (joiner == null) {
            throw new GameFullException();
        }
        return joiner;
    }

    /**
     * Returns the board for the desired game.
     * @param gid the gameId
     * @return the board
     * @throws IDInvalidException if bad game ID
     */
    public Board getBoard(String gid) throws IDInvalidException {
        Game game = this.theGames.get(gid);
        if (game == null) {
            throw new IDInvalidException();
        }
        return game.board;
    }

    /**
     * Returns the state for the desired game.
     * @param gid the gameId
     * @return the state
     * @throws IDInvalidException if bad game ID
     */
    public State getState(String gid) throws IDInvalidException {
        Game game = this.theGames.get(gid);
        if (game == null) {
            throw new IDInvalidException();
        }
        return game.state;
    }

    /**
     * Checks to see if a horizontal move is valid, and calls the
     * appropriate functions to perform it if so.
     * @param gid the game ID
     * @param body the body of the request
     * @throws IDInvalidException if player or game ID is bad
     * @throws BadMoveTurnException if wrong turn or illegal move
     */
    public void horizontalMove(String gid, String body)
            throws IDInvalidException, BadMoveTurnException {
        Move move = new Gson().fromJson(body, Move.class);
        Game game = this.theGames.get(gid);
        if (game == null) {
            throw new IDInvalidException();
        }

        //If the playerId is invalid
        if (!move.playerId.equals("1") && !move.playerId.equals("2")) {
            System.out.println("Invalid playerId: " + move.playerId);
            throw new IDInvalidException();
        }

        //If it is not the correct player's turn
        if (!(move.playerId.equals("1") && game.state.whoseTurn.equals("RED")) &&
                !(move.playerId.equals("2") && game.state.whoseTurn.equals("BLUE"))) {
            System.out.println("Wrong turn " + move.playerId + " " + game.state.whoseTurn);
            throw new BadMoveTurnException();
        }

        if (game.board.moveHorizontal(move)) {
            //valid move
            int scores = game.board.checkHoriScore(move);
            //scores will be >0 if the move filled a box
            if (scores > 0) {
                if (move.playerId.equals("1")) {
                    game.state.scoreForRed(scores);
                } else {
                    game.state.scoreForBlue(scores);
                }
                if (game.state.state == "FINISHED") {
                    System.out.println("Game over!");
                }
            } else {
                //next turn if no scores
                game.state.nextTurn();
            }
        } else {
            //invalid move
            System.out.println("invalid move");
            throw new BadMoveTurnException();
        }
    }

    /**
     * Checks to see if a vertical move is valid, and calls the
     * appropriate functions to perform it if so.
     * @param gid the game ID
     * @param body the body of the request
     * @throws IDInvalidException if player or game ID is bad
     * @throws BadMoveTurnException if wrong turn or illegal move
     */
    public void verticalMove(String gid, String body)
            throws IDInvalidException, BadMoveTurnException {
        Move move = new Gson().fromJson(body, Move.class);
        Game game = this.theGames.get(gid);
        if (game == null) {
            throw new IDInvalidException();
        }

        //If the playerId is invalid
        if (!move.playerId.equals("1") && !move.playerId.equals("2")) {
            System.out.println("Invalid playerId: " + move.playerId);
            throw new IDInvalidException();
        }

        //If it is not the correct player's turn
        if (!(move.playerId.equals("1") && game.state.whoseTurn.equals("RED")) &&
                !(move.playerId.equals("2") && game.state.whoseTurn.equals("BLUE"))) {
            System.out.println("Wrong turn" + move.playerId + " " + game.state.whoseTurn);
            throw new BadMoveTurnException();
        }

        if (game.board.moveVertical(move)) {
            //valid move
            int scores = game.board.checkVertScore(move);
            //scores will be >0 if the move filled a box
            if (scores > 0) {
                if (move.playerId.equals("1")) {
                    game.state.scoreForRed(scores);
                } else {
                    game.state.scoreForBlue(scores);
                }
                if (game.state.state == "FINISHED") {
                    System.out.println("Game over!");
                }
            } else {
                //next turn if no scores
                game.state.nextTurn();
            }
        } else {
            //invalid move
            System.out.println("invalid move");
            throw new BadMoveTurnException();
        }
    }

}
