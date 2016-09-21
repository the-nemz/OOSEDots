package com.oose2016.inemzer1.dots;

/**
 * Class to hold data about a player.
 * Created by Isaac on 9/19/2016.
 */
public class Player {

    public String gameId;
    public String playerId;
    public String playerType;

    /**
     * Simple constructor for a player.
     * @param gid the gameId
     * @param pid the playerId
     * @param pt the playerType
     */
    public Player(String gid, String pid, String pt) {
        this.gameId = gid;
        this.playerId = pid;
        this.playerType = pt;
    }
}
