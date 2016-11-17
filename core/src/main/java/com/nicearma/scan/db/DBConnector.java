package com.nicearma.scan.db;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;

/**
 * Created by nicea on 23/10/2016.
 */
public class DBConnector extends AbstractVerticle {


    protected JDBCClient jdbc;

    @Override
    public void start() throws Exception {
        this.jdbc = JDBCClient.createShared(vertx, new JsonObject()
                .put("url", "jdbc:h2:file:C:/Users/nicea/Documents/database/data")
                .put("driver_class", "org.h2.Driver"));
    }


    public JDBCClient getJbdc(){
        return this.jdbc;
    }

}
