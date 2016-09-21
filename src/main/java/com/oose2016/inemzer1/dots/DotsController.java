package com.oose2016.inemzer1.dots;

import java.util.Collections;
import static spark.Spark.*;

/**
 * Handles all routing for the Dots game.
 * Based on the to do app provided to us at
 * https://github.com/jhu-oose/todo
 * Created by Isaac on 9/18/2016.
 */
public class DotsController {

    private static final String API_CONTEXT = "/dots/api";

    private DotsService service;

    /**
     * Simple contructor.
     * @param dotsService the DotsService
     */
    public DotsController(DotsService dotsService) {
        this.service = dotsService;
        setupEndpoints();
    }

    /**
     * Handles all of the routing.
     */
    private void setupEndpoints() {
        post(API_CONTEXT + "/games", "application/json", (request, response) -> {
            try {
                response.status(201);
                return this.service.createGame(request.body());
            } catch(Exception ex) {
                //unexpected exception
                response.status(500);
            }
            return Collections.EMPTY_MAP;
        }, new JsonTransformer());

        put(API_CONTEXT + "/games/:gameId", "application/json", (request, response) -> {
            String gid = request.params(":gameId");
            try {
                response.status(200);
                return this.service.joinGame(gid);
            } catch (IDInvalidException ex) {
                //bad gameId
                response.status(404);
                return Collections.EMPTY_MAP;
            } catch (GameFullException ex) {
                //game is full
                response.status(410);
                return Collections.EMPTY_MAP;
            }
        }, new JsonTransformer());

        get(API_CONTEXT + "/games/:gameId/board", "application/json", (request, response) -> {
            String gid = request.params(":gameId");
            try {
                response.status(200);
                return this.service.getBoard(gid);
            } catch (IDInvalidException ex) {
                //bad gameId
                response.status(404);
                return Collections.EMPTY_MAP;
            }
        }, new JsonTransformer());

        get(API_CONTEXT + "/games/:gameId/state", "application/json", (request, response) -> {
            String gid = request.params(":gameId");
            try {
                response.status(200);
                return this.service.getState(gid);
            } catch (IDInvalidException ex) {
                //bad gameId
                response.status(404);
                return Collections.EMPTY_MAP;
            }
        }, new JsonTransformer());

        post(API_CONTEXT + "/games/:gameId/hmove", "application/json", (request, response) -> {
            String gid = request.params(":gameId");
            try {
                response.status(200);
                this.service.horizontalMove(gid, request.body());
            } catch (IDInvalidException ex) {
                //bad gameId or playerId
                response.status(404);
            } catch (BadMoveTurnException ex) {
                //wrong turn or illegal move
                response.status(422);
            }
            return Collections.EMPTY_MAP;
        }, new JsonTransformer());

        post(API_CONTEXT + "/games/:gameId/vmove", "application/json", (request, response) -> {
            String gid = request.params(":gameId");
            try {
                response.status(200);
                this.service.verticalMove(gid, request.body());
            } catch (IDInvalidException ex) {
                //bad gameId or playerId
                response.status(404);
            } catch (BadMoveTurnException ex) {
                //wrong turn or illegal move
                response.status(422);
            }
            return Collections.EMPTY_MAP;
        }, new JsonTransformer());

    }
}
