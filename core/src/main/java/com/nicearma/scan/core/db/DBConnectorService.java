package com.nicearma.scan.core.db;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;


public class DBConnectorService {


    JDBCClient jdbc;

    public DBConnectorService(){

        jdbc= JDBCClient.createShared(Vertx.vertx(), new JsonObject()
                .put("url", "jdbc:h2:mem:scan")
                .put("driver_class", "org.h2.Driver"));
    }

    public JDBCClient getJdbc() {
        return jdbc;
    }
}
