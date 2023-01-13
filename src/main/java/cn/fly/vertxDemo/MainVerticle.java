package cn.fly.vertxDemo;

import cn.fly.vertxDemo.verticle.MyFirstVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

/**
 * @author fly
 * @date 2023/1/10
 * @description
 */

public class MainVerticle extends AbstractVerticle {

    public void start() {
        vertx.deployVerticle(MyFirstVerticle.class.getName(), new DeploymentOptions().setInstances(20));
    }




}
