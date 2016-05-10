package nl.revolution.vertx3.callbacks;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import nl.revolution.vertx3.services.*;

import static nl.revolution.vertx3.utils.LoggingUtils.configureLogging;

public class CallbacksVertxStarter extends AbstractVerticle {

    public static void main(String... args) {
        configureLogging();

        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Service1());
        vertx.deployVerticle(new Service2());
        vertx.deployVerticle(new Service3());
        vertx.deployVerticle(new CallbacksWebVerticle());
    }

}
