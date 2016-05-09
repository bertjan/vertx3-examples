vertx.setPeriodic(1000, function (id) {
  vertx.eventBus().publish("events-feed", "server event - from JavaScript!");
});