package com.nicearma.scan.http;

import com.nicearma.scan.core.ConsumerDB;
import com.nicearma.scan.core.Scan;
import com.nicearma.scan.core.db.DBConnectorService;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.jboss.weld.vertx.WeldVerticle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) throws SQLException {

        Vertx vertx = Vertx.vertx();

        final WeldVerticle weldVerticle = new WeldVerticle();
        vertx.deployVerticle(weldVerticle, result -> {
            if (result.succeeded()) {
                //weldVerticle.container().select(DBConnectorService.class).get().
                vertx.deployVerticle(weldVerticle.container().select(Scan.class).get());
                vertx.deployVerticle(weldVerticle.container().select(ConsumerDB.class).get());

                vertx.deployVerticle(weldVerticle.container().select(ConsumerHttp.class).get());
                vertx.deployVerticle(weldVerticle.container().select(Rest.class).get());
            }
        });




    }

}
