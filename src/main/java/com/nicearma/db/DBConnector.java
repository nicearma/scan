package com.nicearma.db;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;

/**
 * Created by nicea on 23/10/2016.
 */
public class DBConnector extends AbstractVerticle {


    protected JDBCClient jdbc;

    @Override
    public void start() throws Exception {
        this.jdbc = JDBCClient.createShared(vertx, new JsonObject()
                .put("url", "jdbc:h2:file:C:/Users/nicea/Documents/database")
                .put("driver_class", "org.h2.Driver"));
    }


    public JDBCClient getJbdc(){
        return this.jdbc;
    }

}
