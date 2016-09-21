package com.oose2016.inemzer1.dots;

import java.util.Collections;
import static spark.Spark.*;

/**
 * Created by Isaac on 9/18/2016.
 */
public class DotsController {

    private static final String API_CONTEXT = "/dots/api";

    private DotsService service;

    public DotsController(DotsService dotsService) {
        this.service = dotsService;
        setupEndpoints();
    }

    private void setupEndpoints() {
        post(API_CONTEXT + "/games", "application/json", (request, response) -> {
            try {
                response.status(201);
                return this.service.createGame(request.body());
            } catch(Exception ex) {
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
                response.status(404);
                return Collections.EMPTY_MAP;
            } catch (GameFullException ex) {
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
                response.status(404);
                return Collections.EMPTY_MAP;
            }
        }, new JsonTransformer());

        post(API_CONTEXT + "/games/:gameId/hmove", "application/json", (request, response) -> {
            String gid = request.params(":gameId");
            System.out.println("\nroute game/:gameId/hmove");
            try {
                response.status(200);
                this.service.horizontalMove(gid, request.body());
                System.out.println("Successfully moved horizontal.");
            } catch (IDInvalidException ex) {
                System.out.println("Failed to move horizontal.");
                response.status(404);
            } catch (BadMoveTurnException ex) {
                System.out.println("Failed to move horizontal.");
                response.status(422);
            }
            return Collections.EMPTY_MAP;
        }, new JsonTransformer());

        post(API_CONTEXT + "/games/:gameId/vmove", "application/json", (request, response) -> {
            String gid = request.params(":gameId");
            System.out.println("\nroute game/:gameId/vmove");
            try {
                response.status(200);
                this.service.verticalMove(gid, request.body());
                System.out.println("Successfully moved vertical.");
            } catch (IDInvalidException ex) {
                System.out.println("Failed to move vertical.");
                response.status(404);
            } catch (BadMoveTurnException ex) {
                System.out.println("Failed to move vertical.");
                response.status(422);
            }
            return Collections.EMPTY_MAP;
        }, new JsonTransformer());

    }
}
