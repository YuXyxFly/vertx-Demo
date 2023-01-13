package cn.fly.vertxDemo.start;

import io.vertx.core.Vertx;

import java.util.function.Consumer;

/**
 * @author fly
 * @date 2023/1/13
 * @description
 */

public interface Launcher {

    void start(Consumer<Vertx> consumer);

}
