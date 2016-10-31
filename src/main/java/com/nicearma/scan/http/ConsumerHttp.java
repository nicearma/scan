package com.nicearma.scan.http;

import com.nicearma.scan.Scan;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * Created by nicea on 08/10/2016.
 */
public class ConsumerHttp  extends AbstractVerticle  {


    Logger logger = LoggerFactory.getLogger(ConsumerHttp.class);

    @Override
    public void start() throws Exception {
        this.vertx.eventBus().consumer(Scan.FILE_PROPS, message -> {
            this.vertx.eventBus().publish(Rest.HTTP_FILE_PROPS,message.body());
        });

        this.vertx.eventBus().consumer(Scan.DIR_SCANED, message -> {
            JsonObject json= new JsonObject();
            json.put(Scan.JSON_PATH,message.body() );
            this.vertx.eventBus().publish(Rest.HTTP_DIR_SCANED,json);
        });
    }

}
