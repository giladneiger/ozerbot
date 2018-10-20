package com.psu;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;

/**
 * Created by Omri on 10/20/2018
 */

public class Server extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        Context context = Vertx.currentContext();

        Server server = new Server();
        server.init(vertx, context);
        server.start();
    }

    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!");
        }).listen(8080);
    }
}
