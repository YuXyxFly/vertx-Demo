package cn.fly.vertxDemo;

import cn.fly.vertxDemo.verticle.MyFirstVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author fly
 * @date 2023/1/10
 * @description
 */

@RunWith(VertxUnitRunner.class)
public class MyFirstVerticleTest {

    private Vertx vertx;

    @Before
    public void setup(TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(MyFirstVerticle.class.getName());
        context.asyncAssertSuccess();
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testApplication(TestContext context) {
        final Async async = context.async();

        vertx.createHttpClient().getNow(9090, "130.10.7.157", "/", response -> {
            response.handler( body -> {
                context.assertTrue(body.toString().contains("Hello"));
                async.complete();
            });
        });
    }

}
