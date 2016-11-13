package com.nicearma.scan.json;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonFile extends JsonPath implements Json{

    private long size;
    public static final String KEY_FILE_SIZE="size";

    private JsonFile(JsonObject jsonObject) {
        super(jsonObject);
        this.size=jsonObject.getLong(KEY_FILE_SIZE);
    }

    private JsonFile(String path, String pathOriginal) {
        super(path, pathOriginal);
    }

    public JsonFile(String path, String pathOriginal, long size) {
        this(path, pathOriginal);
        this.size=size;
    }

    public static JsonObject getJsonObject(String path, String pathOriginal,long size){
        return new JsonFile(path,pathOriginal,size).getJsonObject();
    }


    @Override
    public JsonObject getJsonObject() {
        JsonObject jsonFile= super.getJsonObject();
        jsonFile.put(KEY_FILE_SIZE,size);
        return jsonFile;
    }

    @Override
    public JsonArray getJsonArray() {
        return super.getJsonArray().add(size);
    }
}
