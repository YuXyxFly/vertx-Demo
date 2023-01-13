package cn.fly.vertxDemo.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;



/**
 * @author fly
 * @date 2023/1/10
 * @description
 */

public class MyFirstVerticle extends AbstractVerticle {

    Logger logger = LoggerFactory.getLogger(MyFirstVerticle.class);

    public void start() {
        System.out.println((Thread.currentThread().getName() + "; is starting :" + Thread.currentThread().getId()) + "------" + this.getVertx().deploymentIDs());
        vertx.createHttpServer().requestHandler(req -> {
            //logger.info(Thread.currentThread().getName() + " is answering the req!");
           req.response().putHeader("content-type", "text/plain")
                   .end(Thread.currentThread().getName() + " is answering the req!");
        }).listen(9090);
    }
}
