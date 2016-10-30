package com.nicearma;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.jdbc.JDBCClient;

/**
 * Created by nicea on 05/10/2016.
 */
public class ConsumerLogger extends AbstractVerticle {

    Logger logger = LoggerFactory.getLogger(ConsumerLogger.class);

    // Called when verticle is deployed
    public void start() {

        this.vertx.eventBus().consumer("fileProps", message -> {
            JsonArray params=(JsonArray) message.body();
            logger.info(params);

         });

    }

}
