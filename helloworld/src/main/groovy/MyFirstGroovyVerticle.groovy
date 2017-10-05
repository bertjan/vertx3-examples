vertx.createHttpServer()
        .requestHandler({ req -> req.response().end("Hello world from Groovy!") })
        .listen(8080)