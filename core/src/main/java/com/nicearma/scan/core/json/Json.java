package com.nicearma.scan.core.json;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public interface Json {

    JsonObject getJsonObject();

    JsonArray getJsonArray();

}
