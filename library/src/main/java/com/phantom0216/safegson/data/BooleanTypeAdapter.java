package com.phantom0216.safegson.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class BooleanTypeAdapter extends TypeAdapter<Boolean> {

    @Override
    public Boolean read(JsonReader in) throws IOException {
        switch (in.peek()) {
            case BOOLEAN:
                return in.nextBoolean();
            case STRING:
                // 如果后台返回 "true" 或者 "TRUE"，则处理为 true，否则为 false
                return Boolean.parseBoolean(in.nextString());
            case NUMBER:
                // 如果后台返回的是非 0 的数值则处理为 true，否则为 false
                return in.nextInt() != 0;
            case NULL:
                in.nextNull();
                return null;
            default:
                in.skipValue();
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void write(JsonWriter out, Boolean value) throws IOException {
        out.value(value);
    }
}