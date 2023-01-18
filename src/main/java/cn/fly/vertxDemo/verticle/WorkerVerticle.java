package cn.fly.vertxDemo.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

/**
 * @author fly
 * @date 2023/1/18
 * @description
 */

public class WorkerVerticle extends AbstractVerticle {

    /**
     * If your verticle does a simple, synchronous start-up then override this method and put your start-up
     * code in there.
     *
     * @throws Exception
     */
    @Override
    public void start() throws Exception {
        System.out.println(Thread.currentThread().getName() + ", Start Worker...");
        final EventBus event = this.vertx.eventBus();

        event.<JsonObject>consumer("MSG://EVENT/BUS", reply -> {
            System.out.println(Thread.currentThread().getName() + ", Consumer Message...");

            final JsonObject message = reply.body();
            System.out.println("Message: " + message.encode());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            reply.reply(new JsonObject().put("worker", "Worker Message"));
        });
    }
}
