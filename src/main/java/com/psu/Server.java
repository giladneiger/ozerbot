package com.psu;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omri on 10/20/2018
 */

public class Server extends AbstractVerticle {

    private static final String COMMAND = "command";
    private OzerBot ozerBot;

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        Context context = Vertx.currentContext();
        Server server = new Server();
        server.initBot();
        server.init(vertx, context);
        server.start();
    }

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.get("/:command").handler(this::botHandler);
        vertx.createHttpServer().requestHandler(req -> req.response()
                .putHeader("content-type", "text/plain")
                .end("Hello from Vert.x!")).listen(8080);
    }

    private void initBot() {
        Map<String, FeatureHandler> features = initializeFeatures();
        ozerBot = new OzerBot(features);
    }

    private Map<String, FeatureHandler> initializeFeatures() {
        HashMap<String, FeatureHandler> featureHandlerMap = new HashMap<>();
        featureHandlerMap.put("drunk", new DrunkFeature());
        featureHandlerMap.put("neiger", new NeigerFeature());
        return featureHandlerMap;
    }

    private void botHandler(RoutingContext routingContext) {
        String message = "";
        String theCommand = routingContext.pathParam(COMMAND);
        if (theCommand != null) {
            message = ozerBot.sayToOzer(theCommand);
        }
        JsonObject json = new JsonObject().put("message", message);
        routingContext.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .end(json.encode());
    }
}
