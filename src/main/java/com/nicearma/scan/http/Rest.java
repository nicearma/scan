package com.nicearma.scan.http;


import com.nicearma.scan.Scan;
import com.nicearma.scan.db.DBConnector;
import com.nicearma.scan.db.DBSql;
import com.nicearma.scan.json.JsonPath;
import io.netty.util.internal.StringUtil;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileProps;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Rest extends AbstractVerticle {

    Router router;
    protected DBConnector dbConnector;
    Logger logger = LoggerFactory.getLogger(Rest.class);
    public static final String EVENT_HTTP_FILE_PROPS = "eventHttpFileProps";
    public static final String EVENT_HTTP_DIR_SCANED = "eventHttpDirScaned";


    public Rest(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }


    @Override
    public void start() throws Exception {
        super.start();
        HttpServer server = vertx.createHttpServer();
        this.router = Router.router(vertx);
        router.route().handler(CorsHandler.create("*")
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.OPTIONS)
                .allowedHeader("X-PINGARUNER")
                .allowedHeader("Content-Type"));
        router.route().handler(BodyHandler.create());
        addSocketSendScanFolder();
        addRouteScanDir();
        addGetBiggestFile();
        addRouteOpenPath();
        server.requestHandler(router::accept).listen(8080);
    }

    public void addRouteScanDir() {

        router.post("/scan").handler(handler -> {
            JsonObject jsonIn = handler.getBodyAsJson();
            if (StringUtil.isNullOrEmpty(jsonIn.getString(JsonPath.KEY_PATH))) {
                reponseBadRequest(handler.response());
                return;
            }

            this.vertx.fileSystem().exists(jsonIn.getString(JsonPath.KEY_PATH), exists -> {

                if (exists.result()) {
                    JsonObject pathInfo = new JsonObject();
                    pathInfo.put(JsonPath.KEY_PATH, jsonIn.getString(JsonPath.KEY_PATH));
                    vertx.eventBus().send(Scan.EVENT_PATH, pathInfo);
                    reponseOk(handler.response());
                } else {
                    reponseBadRequest(handler.response());
                }

            });


        });
    }

    protected void reponseBadRequest(HttpServerResponse response) {
        response.setStatusCode(404).end();
        return;
    }

    protected void reponseOk(HttpServerResponse response) {
        response.setStatusCode(200).end();
        return;
    }

    public void addRouteOpenPath() {

        router.post("/path/open").handler(handler -> {
            JsonObject jsonIn = handler.getBodyAsJson();
            String path = jsonIn.getString(JsonPath.KEY_PATH);
            if (path != null) {
                vertx.fileSystem().exists(path, pathResult -> {
                    try {
                        Desktop.getDesktop().open(new File(path));
                    } catch (IOException e) {
                        logger.error("cannot open path", path);
                        logger.error(e);
                    }

                });
            }


        });
    }

    public void addGetBiggestFile() {

        router.post("/files").handler(handler -> {
            JsonArray pagination = new JsonArray();
            JsonObject jsonIn = null;
            if (handler.getBody() != null && handler.getBody().length() > 0) {
                jsonIn = handler.getBodyAsJson();
            }

            if (jsonIn != null && jsonIn.getInteger("page") != null) {
                pagination.add(jsonIn.getInteger("page"));
            } else {
                pagination.add(0);
            }
            if (jsonIn != null && jsonIn.getInteger("quantity") != null) {
                pagination.add(jsonIn.getInteger("quantity"));
            } else {
                pagination.add(20);
            }


            this.dbConnector.getJbdc().getConnection(resultConnection -> {

                if (resultConnection.succeeded()) {
                    resultConnection.result().queryWithParams(DBSql.GET_BIGGEST_FILE, pagination, resultSelect -> {
                        if (resultSelect.succeeded()) {
                            HttpServerResponse response = handler.response();
                            response.putHeader("content-type", "application/json");
                            Buffer output = Buffer.buffer(resultSelect.result().toJson().getJsonArray("rows").encode());
                            response.putHeader("Content-Length", String.valueOf(output.length()));

                            response.write(output).end();
                        }
                    });
                }

            });

        });
    }

    protected void addSocketSendScanFolder() {
        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        BridgeOptions options = new BridgeOptions()
                .addOutboundPermitted(new PermittedOptions().setAddressRegex("http.*"));
        sockJSHandler.bridge(options);
        router.route("/api/*").handler(sockJSHandler);
    }

}
