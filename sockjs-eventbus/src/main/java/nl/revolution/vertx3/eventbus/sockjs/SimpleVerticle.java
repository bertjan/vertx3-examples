package nl.revolution.vertx3.eventbus.sockjs;

import io.vertx.core.AbstractVerticle;

public class SimpleVerticle extends AbstractVerticle {

    @Override
    public void start() {
        vertx.setPeriodic(1000, t -> vertx.eventBus().publish("events-feed", "server event - from Java!"));
    }

}
