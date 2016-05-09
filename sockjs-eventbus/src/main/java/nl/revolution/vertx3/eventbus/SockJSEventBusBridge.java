package nl.revolution.vertx3.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.*;

public class SockJSEventBusBridge extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(SockJSEventBusBridge.class);

    @Override
    public void start() {
        LOG.info("started");

        configureEventBusBrige();
    }

    private void configureEventBusBrige() {
        Router router = Router.router(vertx);

        BridgeOptions options = new BridgeOptions().addOutboundPermitted(new PermittedOptions().setAddress("events-feed"));
        router.route("/eventbus/*").handler(SockJSHandler.create(vertx).bridge(options));

        router.route().handler(StaticHandler.create("web"));

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }

}
