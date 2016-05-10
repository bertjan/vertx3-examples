package nl.revolution.vertx3.eventbus.sockjs;

import io.vertx.core.*;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.logging.SLF4JLogDelegateFactory;

public class VertxStarter extends AbstractVerticle {

    public static void main(String... args) {
        System.setProperty(LoggerFactory.LOGGER_DELEGATE_FACTORY_CLASS_NAME, SLF4JLogDelegateFactory.class.getName());

        VertxOptions options = new VertxOptions().setClustered(true).setClusterHost("localhost");

        Vertx.clusteredVertx(options, resultHandler -> {
            Vertx vertx = resultHandler.result();
            vertx.deployVerticle(new SockJSEventBusBridge());
            vertx.deployVerticle(new SimpleVerticle());
            vertx.deployVerticle("js/simpleVerticle.js");
        });
    }

}
