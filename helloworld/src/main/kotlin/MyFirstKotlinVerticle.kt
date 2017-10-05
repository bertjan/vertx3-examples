import io.vertx.core.*
import io.vertx.kotlin.core.http.HttpServerOptions

class MyFirstKotlinVerticle : AbstractVerticle() {

    override fun start() {
        vertx.createHttpServer()
                .requestHandler({ req -> req.response().end("Hello world from Kotlin!") })
                .listen(8080)
    }
}