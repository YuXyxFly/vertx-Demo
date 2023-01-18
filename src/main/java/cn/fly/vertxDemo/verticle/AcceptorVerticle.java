package cn.fly.vertxDemo.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;

/**
 * @author fly
 * @date 2023/1/18
 * @description
 */

public class AcceptorVerticle extends AbstractVerticle {

    /**
     * If your verticle does a simple, synchronous start-up then override this method and put your start-up
     * code in there.
     *
     * @throws Exception
     */
    @Override
    public void start() throws Exception {

        final HttpServer server = this.vertx.createHttpServer();
        System.out.println(Thread.currentThread().getName() + ", start Acceptor...");
        server.requestHandler(request -> {

            final EventBus eventBus = this.vertx.eventBus();
            System.out.println(Thread.currentThread().getName() + ", Accept Request...");

            eventBus.<JsonObject>send("MSG://EVENT/BUS",
                    new JsonObject().put("message", "Event Communication"),
                    reply -> {
                            if (reply.succeeded()) {
                                System.out.println(Thread.currentThread().getName() + ", Reply Message...");
                                System.out.println(" Message: " + reply.result().body());
                                request.response().end(reply.result().body().encode());
                            }
                    }
                    );
        });

        server.listen(9090);

    }
}
