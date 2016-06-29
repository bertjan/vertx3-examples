package nl.revolution.vertx3.eventbus.sockjs;

import io.vertx.core.*;

import static nl.revolution.vertx3.utils.LoggingUtils.configureLogging;

public class VertxStarter extends AbstractVerticle {

    public static void main(String... args) {
        configureLogging();

        VertxOptions options = new VertxOptions().setClustered(true).setClusterHost("localhost");

        Vertx.clusteredVertx(options, resultHandler -> {
            Vertx vertx = resultHandler.result();
            vertx.deployVerticle(new SockJSEventBusBridge());
            vertx.deployVerticle(new SimpleVerticle());
            vertx.deployVerticle("js/simpleVerticle.js");
        });
    }

}
