package com.nicearma.scan.core;

import com.nicearma.scan.core.json.JsonFile;
import com.nicearma.scan.core.json.JsonPath;
import io.netty.util.internal.StringUtil;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.file.FileProps;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.jdbc.JDBCClient;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.HashSet;

@Dependent
public class Scan extends AbstractVerticle {

    Logger logger = LoggerFactory.getLogger(Scan.class);

    public static final String EVENT_PATH ="eventPath";
    public static final String EVENT_DIR_PROPS ="eventDirProps";
    public static final String EVENT_FILE_PROPS ="eventFileProps";
    public static final String EVENT_DIR_SCANED ="eventDirScaned";

    @Inject
    FilterService filterService;


    // Called when verticle is deployed
    public void start() {


        this.vertx.eventBus().consumer(EVENT_PATH, message -> {

            JsonPath jsonPath= new JsonPath((JsonObject) message.body());

            if(StringUtil.isNullOrEmpty(jsonPath.getPath())){
                logger.error("EmptyPathError:",message.body());
                return;
            }

            if(filterService.isFiltered(jsonPath.getPath())){
                return;
            }

            //logger.info(path);
            vertx.fileSystem().props(jsonPath.getPath(), props -> {
                //logger.info(path);
                if (props.succeeded()) {
                    if (props.result().isDirectory() && !props.result().isSymbolicLink()) {
                        vertx.fileSystem().readDir(jsonPath.getPath(), resultDir -> {
                            if (resultDir.succeeded()) {
                                if(resultDir.result()!=null && !resultDir.result().isEmpty()){
                                    resultDir.result().stream().forEach(pathToScan -> {
                                        sendPath(pathToScan,jsonPath.getPath());
                                    });
                                    //This is not really truth
                                    sendDirScaned(jsonPath.getPath());
                                    sendPathProps(props.result(),jsonPath.getPath());
                                }

                            } else {
                                logger.error("ReadError:"+jsonPath.getPath(),resultDir.cause());
                            }
                        });
                    } else if (props.result().isRegularFile()) {
                        sendFileProps(props.result(),jsonPath);
                    }

                }else{
                    logger.error("PropsError:"+jsonPath.getPath(),props);
                }
             });


        });
    }

    private void sendPathProps(FileProps fileProps,String path){
        JsonArray params = new JsonArray();
        params.add(path);
        params.add(fileProps.size());
        this.vertx.eventBus().publish(EVENT_DIR_PROPS, params);
    }

    private void sendDirScaned(String path){
        this.vertx.eventBus().publish(EVENT_DIR_SCANED, path);
    }

    private void sendFileProps(FileProps fileProps, JsonPath jsonPath){
        JsonFile jsonFile = new JsonFile(jsonPath.getPath(),jsonPath.getPathOriginal(),fileProps.size());
        jsonFile.cleanFilePath();
        this.vertx.eventBus().publish(EVENT_FILE_PROPS, jsonFile.getJsonArray());
    }

    private void sendPath(String pathToScan ,String pathOriginal){
        JsonObject pathInfoSend=JsonPath.getJsonObject(pathToScan,pathOriginal);
        this.vertx.eventBus().send(EVENT_PATH, pathInfoSend);
    }

}
