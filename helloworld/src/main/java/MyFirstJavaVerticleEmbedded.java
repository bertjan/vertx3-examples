import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class MyFirstJavaVerticleEmbedded {

    private static final Logger LOG = LoggerFactory.getLogger(MyFirstJavaVerticleEmbedded.class);

    public static void main(String... args) {
        LOG.info("Starting embedded Vert.x");

        Vertx.vertx().
                createHttpServer()
                .requestHandler(request -> request.response().end("Hello world from Java with embedded Vert.x!"))
                .listen(8080);
    }

}
