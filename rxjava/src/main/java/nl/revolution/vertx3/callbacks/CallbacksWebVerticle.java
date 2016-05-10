package nl.revolution.vertx3.callbacks;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import nl.revolution.vertx3.services.*;

public class CallbacksWebVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(CallbacksWebVerticle.class);

    @Override
    public void start() {
        LOG.info("Starting CallbacksWebVerticle");
        vertx.createHttpServer().requestHandler(new RequestHandler()).listen(8080);
    }


    private class RequestHandler implements Handler<HttpServerRequest> {

        @Override
        public void handle(HttpServerRequest request) {
            // Send a message to service 1.
            vertx.eventBus().send(Service1.ADDRESS, "message-to-service1", reply1 -> {
                String replyFromService1 = (String) reply1.result().body();

                // Send a message to service 2.
                vertx.eventBus().send(Service2.ADDRESS, "message-to-service2", reply2 -> {
                    String replyFromService2 = (String) reply2.result().body();

                    // Send the result from both service calls to service 3.
                    String combinedResult = replyFromService1 + "-" + replyFromService2;
                    vertx.eventBus().send(Service3.ADDRESS, combinedResult, reply3 -> {

                        // Send an http response with the result of service 3.
                        request.response().end("CallbacksWebVerticle - response from Service 3: " + reply3.result().body());
                    });
                });
            });
        }
    }

}
