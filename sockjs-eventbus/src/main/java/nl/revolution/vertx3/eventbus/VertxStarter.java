package nl.revolution.vertx3.eventbus;

import io.vertx.core.*;

public class VertxStarter extends AbstractVerticle {

    public static void main(String... args) {
        VertxOptions options = new VertxOptions().setClustered(true).setClusterHost("localhost");

        Vertx.clusteredVertx(options, resultHandler -> {
            Vertx vertx = resultHandler.result();
            vertx.deployVerticle(new SockJSEventBusBridge());
            vertx.deployVerticle(new SimpleVerticle());
            vertx.deployVerticle("js/simpleVerticle.js");
        });
    }

}
