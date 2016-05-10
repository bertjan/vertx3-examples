package nl.revolution.vertx3.services;

import io.vertx.core.AbstractVerticle;

public class Service1 extends AbstractVerticle {

    public static final String ADDRESS = "service1";

    @Override
    public void start() {
        vertx.eventBus().consumer(ADDRESS, event -> event.reply("[reply-from-service1]"));
    }
}
