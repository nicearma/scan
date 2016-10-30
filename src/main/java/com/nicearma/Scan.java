package com.nicearma;

import io.netty.util.internal.StringUtil;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.file.FileProps;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.jdbc.JDBCClient;

/**
 * Created by nicea on 03/10/2016.
 */
public class Scan extends AbstractVerticle {

    Logger logger = LoggerFactory.getLogger(Scan.class);

    public static final String PATH="path";
    public static final String DIR_PROPS ="dirProps";
    public static final String FILE_PROPS="fileProps";
    public static final String DIR_SCANED ="dirScaned";

    public static final String JSON_PATH="path";
    public static final String JSON_PATH_ORIGINAL="pathOriginal";

    JDBCClient jdbc;

    public Scan() {

    }

    // Called when verticle is deployed
    public void start() {


        this.vertx.eventBus().consumer(PATH, message -> {

            JsonObject pathInfo=(JsonObject) message.body();
            String path =pathInfo.getString(JSON_PATH);

            if(StringUtil.isNullOrEmpty(path)){
                logger.error("EmptyPathError:",message.body());
                return;
            }

            //logger.info(path);
            vertx.fileSystem().props(path, props -> {
                //logger.info(path);
                if (props.succeeded()) {
                    if (props.result().isDirectory()) {
                        vertx.fileSystem().readDir(path, resultDir -> {
                            if (resultDir.succeeded()) {
                                if(resultDir.result()!=null && !resultDir.result().isEmpty()){
                                    resultDir.result().stream().forEach(pathToScan -> {
                                        sendPath(pathToScan,path);
                                    });
                                    //This is not really truth
                                    sendDirScaned(path);
                                    sendPathProps(props.result(),path);
                                }

                            } else {
                                logger.error("ReadError:"+path,resultDir.cause());
                            }
                        });
                    } else if (props.result().isRegularFile()) {
                        sendFileProps(props.result(),pathInfo);
                    }

                }else{
                    logger.error("PropsError:"+path,props);
                }
             });


        });
    }

    private void sendPathProps(FileProps fileProps,String path){
        JsonArray params = new JsonArray();
        params.add(path);
        params.add(fileProps.size());
        this.vertx.eventBus().publish(DIR_PROPS, params);
    }

    private void sendDirScaned(String path){
        this.vertx.eventBus().publish(DIR_SCANED, path);
    }

    private void sendFileProps(FileProps fileProps, JsonObject pathInfo){
        JsonArray params = new JsonArray();
        params.add(pathInfo.getString(JSON_PATH_ORIGINAL));
        params.add(pathInfo.getString(JSON_PATH));
        params.add(fileProps.size());
        this.vertx.eventBus().publish(FILE_PROPS, params);

    }

    private void sendPath(String pathToScan ,String pathOriginal){
        JsonObject pathInfoSend= new JsonObject();
        pathInfoSend.put(JSON_PATH_ORIGINAL,pathOriginal);
        pathInfoSend.put(JSON_PATH, pathToScan);
        this.vertx.eventBus().send(PATH, pathInfoSend);
    }




    // Optional - called when verticle is undeployed
    public void stop() {
    }
}
