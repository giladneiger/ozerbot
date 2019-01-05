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

    @Override
    public void start() {
        initBot();
        Router router = Router.router(vertx);
        router.get("/:command").handler(this::botHandler);
        vertx.createHttpServer().requestHandler(router::accept)
                .listen(8080);
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
