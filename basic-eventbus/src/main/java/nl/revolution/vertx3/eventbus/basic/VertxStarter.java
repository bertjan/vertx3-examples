package nl.revolution.vertx3.eventbus.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

import static nl.revolution.vertx3.utils.LoggingUtils.configureLogging;

public class VertxStarter extends AbstractVerticle {

    public static void main(String... args) {
        configureLogging();

        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new EchoServiceVerticle());
        vertx.deployVerticle(new WebVerticle());
    }

}
