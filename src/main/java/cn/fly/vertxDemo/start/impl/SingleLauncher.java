package cn.fly.vertxDemo.start.impl;

import cn.fly.vertxDemo.start.Launcher;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.util.function.Consumer;

/**
 * @author fly
 * @date 2023/1/13
 * @description
 */

public class SingleLauncher implements Launcher {

    @Override
    public void start(Consumer<Vertx> consumer) {
        final VertxOptions options = new VertxOptions();
        final Vertx vertx = Vertx.vertx(options);
        if (null != vertx) {
            consumer.accept(vertx);
        }
    }
}
