import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import static nl.revolution.vertx3.utils.LoggingUtils.configureLogging;

public class MyFirstJavaVerticleEmbedded {

    static {
        configureLogging();
    }

    private static final Logger LOG = LoggerFactory.getLogger(MyFirstJavaVerticleEmbedded.class);

    public static void main(String... args) {
        LOG.info("Starting embedded Vert.x");

        Vertx.vertx()
                .createHttpServer()
                .requestHandler(request -> {
                    LOG.info("handling request");
                    request.response().end("Hello world from Java with embedded Vert.x!");
                }).listen(8080);
    }

}
