package nl.revolution.vertx3.eventbus.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class WebVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(WebVerticle.class);

    @Override
    public void start() {
        LOG.info("Starting webVerticle");

        vertx.createHttpServer().requestHandler(request -> {
            JsonObject message = new JsonObject()
                    .put("text", "Hello world!")
                    .put("queryString", request.query());

            LOG.info("Sending message: " + message);
            vertx.eventBus().send(EchoServiceVerticle.ADDRESS, message, reply -> {

                JsonObject replyBody = (JsonObject) reply.result().body();
                LOG.info("Received reply: " + replyBody);

                request.response().end(replyBody.encodePrettily());
            });

        }).listen(8080);
    }
}
