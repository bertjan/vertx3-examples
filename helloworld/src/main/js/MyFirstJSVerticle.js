vertx.createHttpServer()
        .requestHandler(function (req) { req.response().end('Hello world from JavaScript!'); })
        .listen(8080);