package com.nicearma.scan.http;

import com.nicearma.scan.core.Scan;
import com.nicearma.scan.core.json.JsonPath;
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
        this.vertx.eventBus().consumer(Scan.EVENT_FILE_PROPS, message -> {
            this.vertx.eventBus().publish(Rest.EVENT_HTTP_FILE_PROPS,message.body());
        });

        this.vertx.eventBus().consumer(Scan.EVENT_DIR_SCANED, message -> {
            JsonObject json= new JsonObject();
            json.put(JsonPath.KEY_PATH,message.body() );
            this.vertx.eventBus().publish(Rest.EVENT_HTTP_DIR_SCANED,json);
        });
    }

}
