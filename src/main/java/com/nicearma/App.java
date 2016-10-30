package com.nicearma;

import com.nicearma.db.DBConnector;
import com.nicearma.http.ConsumerHttp;
import com.nicearma.http.Rest;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

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

        List<Future> futures=new ArrayList<>();

        DBConnector dbConnector=new DBConnector();
        addVerticleToFuture(vertx, futures, dbConnector);



        addVerticleToFuture(vertx, futures, new Rest(dbConnector));

        addVerticleToFuture(vertx, futures,new Scan());

        CompositeFuture.all(futures).setHandler(results->{
            addVerticleToFuture(vertx, futures, new ConsumerDB(dbConnector));
            addVerticleToFuture(vertx, futures, new ConsumerHttp());
        });


    }

    protected static void addVerticleToFuture(Vertx vertx, List<Future> futures, Verticle verticle){
        Future future = Future.future();
        vertx.deployVerticle(verticle,future.completer());
        futures.add(future);
    }
}
