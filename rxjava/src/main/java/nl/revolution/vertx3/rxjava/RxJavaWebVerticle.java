package nl.revolution.vertx3.rxjava;


import io.vertx.core.Handler;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.Message;
import io.vertx.rxjava.core.http.HttpServerRequest;
import nl.revolution.vertx3.services.*;
import rx.Observable;


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
            Observable<Message<String>> reply1 = vertx.eventBus().sendObservable(Service1.ADDRESS, "message-to-service1");
            Observable<Message<String>> reply2 = vertx.eventBus().sendObservable(Service2.ADDRESS, "message-to-service2");

            Observable.zip(reply1, reply2, (Message resp1, Message resp2) -> resp1.body() + "-" + resp2.body())
                .subscribe(combinedResult -> vertx.eventBus().sendObservable(Service3.ADDRESS, combinedResult)
                .subscribe(reply3 -> request.response().end("RxJavaWebVerticle - response from Service 3: " + reply3.body())));


            // Or, in a oneliner:
//            vertx.eventBus().sendObservable(Service1.ADDRESS, "message-to-service1")
//                .zipWith(vertx.eventBus().sendObservable(Service2.ADDRESS, "message-to-service2"),
//                        (Message resp1, Message resp2) -> resp1.body() + "-" + resp2.body())
//                .subscribe(combinedResult -> vertx.eventBus().sendObservable(Service3.ADDRESS, combinedResult)
//                .subscribe(reply3 -> request.response().end("RxJavaWebVerticle oneliner - response from Service 3: " + reply3.body())));

        }
    }

}






