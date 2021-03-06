package com.nicearma.scan;

import com.nicearma.scan.db.DBConnector;
import com.nicearma.scan.db.DBSql;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * Created by nicea on 05/10/2016.
 */
public class ConsumerDB  extends AbstractVerticle {

    protected Logger logger = LoggerFactory.getLogger(ConsumerDB.class);
    protected DBConnector dbConnector;

    public ConsumerDB(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    // Called when verticle is deployed
    public void start() {

        dbConnector.getJbdc().getConnection(resultConnection->{

            this.vertx.eventBus().consumer(Scan.EVENT_FILE_PROPS, message -> {
                JsonArray params=(JsonArray) message.body();

                    if(resultConnection.succeeded()){
                        resultConnection.result().updateWithParams(DBSql.INSERT_FILE,params, result->{
                            if(result.succeeded()){
                                logger.debug("insert",params);
                            }else{
                                logger.error("error INSERT_FILE",params.toString());
                                logger.error(result.cause());
                            }
                        });
                    }


            });

            this.vertx.eventBus().consumer(Scan.EVENT_DIR_PROPS, message -> {
                JsonArray params=(JsonArray) message.body();

                if(resultConnection.succeeded()){
                    resultConnection.result().updateWithParams(DBSql.INSERT_DIR,params, result->{
                        if(result.succeeded()){
                            logger.debug("insert",params);
                        }else{
                            logger.error("error INSERT_DIR",params.toString());
                            logger.error(result.cause());
                        }
                    });
                }
            });

            resultConnection.result().execute(
                    DBSql.CREATE_TABLE_FILE, result->{
                        if(result.succeeded()){
                            logger.debug("insert","CREATE TABLE IF NOT EXISTS files");
                        }else{

                            logger.error(result.cause());
                        }

                    });

            resultConnection.result().execute(
                   DBSql.CREATE_TABLE_DIR, result->{
                        if(result.succeeded()){
                            logger.debug("insert","CREATE dir IF NOT EXISTS files");
                        }else{
                            logger.error(result.cause());
                        }

                    });

        });


    }

}
