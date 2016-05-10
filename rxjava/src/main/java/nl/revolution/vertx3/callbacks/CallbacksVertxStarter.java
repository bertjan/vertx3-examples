package nl.revolution.vertx3.callbacks;

import io.vertx.core.*;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.logging.SLF4JLogDelegateFactory;
import nl.revolution.vertx3.services.*;

public class CallbacksVertxStarter extends AbstractVerticle {

    public static void main(String... args) {
        System.setProperty(LoggerFactory.LOGGER_DELEGATE_FACTORY_CLASS_NAME, SLF4JLogDelegateFactory.class.getName());

        VertxOptions options = new VertxOptions().setClustered(true).setClusterHost("localhost");

        Vertx.clusteredVertx(options, resultHandler -> {
            Vertx vertx = resultHandler.result();
            vertx.deployVerticle(new Service1());
            vertx.deployVerticle(new Service2());
            vertx.deployVerticle(new Service3());
            vertx.deployVerticle(new CallbacksWebVerticle());
        });

    }

}
