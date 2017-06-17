package nl.revolution.vertx3.rxjava;


import io.vertx.core.Handler;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.Message;
import io.vertx.rxjava.core.http.HttpServerRequest;
import nl.revolution.vertx3.services.*;
import rx.Single;


public class RxJavaWebVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(RxJavaWebVerticle.class);

    @Override
    public void start() {
        LOG.info("Starting RxJavaWebVerticle");
        vertx.createHttpServer().requestHandler(new RequestHandler()).listen(8080);
    }


    private class RequestHandler implements Handler<HttpServerRequest> {

        @Override
        public void handle(HttpServerRequest request) {
            Single<Message<String>> reply1 = vertx.eventBus().rxSend(Service1.ADDRESS, "message-to-service1");
            Single<Message<String>> reply2 = vertx.eventBus().rxSend(Service2.ADDRESS, "message-to-service2");

            Single<String> combinedResult = Single.zip(reply1, reply2, (resp1, resp2) -> resp1.body() + "-" + resp2.body());

            Single<Message<String>> reply3 = combinedResult.flatMap(result -> vertx.eventBus().rxSend(Service3.ADDRESS, result));

            reply3.subscribe(reply -> request.response().end("RxJavaWebVerticle - response from Service 3: " + reply.body()));


            // Or, in a oneliner:
//            vertx.eventBus().rxSend(Service1.ADDRESS, "message-to-service1")
//                    .zipWith(vertx.eventBus().rxSend(Service2.ADDRESS, "message-to-service2"),
//                            (Message resp1, Message resp2) -> resp1.body() + "-" + resp2.body())
//                    .flatMap(combinedResult -> vertx.eventBus().rxSend(Service3.ADDRESS, combinedResult))
//                    .subscribe(reply3 -> request.response().end("RxJavaWebVerticle oneliner - response from Service 3: " + reply3.body()));
        }
    }

}






