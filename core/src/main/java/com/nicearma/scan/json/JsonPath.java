package com.nicearma.scan.json;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonPath implements Json {

    public static final String KEY_PATH="path";
    public static final String KEY_PATH_ORIGINAL="pathOriginal";

    private String path;
    private String pathOriginal;

    public JsonPath(JsonObject jsonObject) {
        this.path = jsonObject.getString(KEY_PATH);
        this.pathOriginal = jsonObject.getString(KEY_PATH_ORIGINAL);
    }

    public JsonPath(String path, String pathOriginal) {
        this.path = path;
        this.pathOriginal = pathOriginal;
    }

    public static JsonObject getJsonObject(String path, String pathOriginal){
       return new JsonPath(path,pathOriginal).getJsonObject();
    }

    @Override
    public JsonObject getJsonObject(){
        JsonObject jsonPath= new JsonObject();
        jsonPath.put(KEY_PATH, this.path);
        jsonPath.put(KEY_PATH_ORIGINAL, this.pathOriginal);
        return jsonPath;
    }

    @Override
    public JsonArray getJsonArray() {
        JsonArray jsonPath= new JsonArray();
        jsonPath.add(pathOriginal);
        jsonPath.add(path);
        return jsonPath;
    }


}
