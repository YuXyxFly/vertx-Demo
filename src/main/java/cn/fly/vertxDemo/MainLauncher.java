package cn.fly.vertxDemo;

import cn.fly.vertxDemo.start.Launcher;
import cn.fly.vertxDemo.start.impl.ClusterLauncher;
import cn.fly.vertxDemo.start.impl.SingleLauncher;
import cn.fly.vertxDemo.verticle.AcceptorVerticle;
import cn.fly.vertxDemo.verticle.WorkerVerticle;
import io.vertx.core.DeploymentOptions;


/**
 * @author fly
 * @date 2023/1/13
 * @description
 */

public class MainLauncher {

    public static void main(String[] args) {
        final boolean isClustered = false;
        final Launcher launcher = isClustered ? new ClusterLauncher() : new SingleLauncher();
        // 设置options
        launcher.start(vertx -> {
            vertx.deployVerticle(AcceptorVerticle.class.getName(), new DeploymentOptions().setInstances(4));

            vertx.deployVerticle(WorkerVerticle.class.getName(), new DeploymentOptions().setInstances(16));
        });
    }

}
