package com.nicearma.scan.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * Created by nicea on 05/10/2016.
 */
public class ConsumerLogger extends AbstractVerticle {

    Logger logger = LoggerFactory.getLogger(ConsumerLogger.class);

    // Called when verticle is deployed
    public void start() {

        this.vertx.eventBus().consumer(Scan.EVENT_FILE_PROPS, message -> {
            JsonArray params=(JsonArray) message.body();
            logger.info(params);

         });

    }

}
