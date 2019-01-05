package com.psu;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omri on 10/20/2018
 */

public class Server extends AbstractVerticle {

    private OzerBot ozerBot;
    private Map<String, FeatureHandler> featureHandlerMap;

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
        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!");
        }).listen(8080);
    }

    private void initBot() {
        featureHandlerMap = initializeFeatures();
        ozerBot = new OzerBot(featureHandlerMap);
    }

    private Map<String,FeatureHandler> initializeFeatures()
    {
        HashMap<String, FeatureHandler> featureHandlerMap = new HashMap<>();
        featureHandlerMap.put("drunk", new DrunkFeature());
        featureHandlerMap.put("neiger", new NeigerFeature());
        return featureHandlerMap;
    }

    private void botHandler(RoutingContext routingContext) {

    }
}
