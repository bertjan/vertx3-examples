import io.vertx.core.AbstractVerticle;

public class MyFirstJavaVerticle extends AbstractVerticle {

    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(req -> req.response().end("Hello world from Java!")).listen(8080);
    }
}
