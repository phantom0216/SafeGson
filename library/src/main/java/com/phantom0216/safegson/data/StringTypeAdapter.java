package com.phantom0216.safegson.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class StringTypeAdapter extends TypeAdapter<String> {

    @Override
    public String read(JsonReader in) throws IOException {
        switch (in.peek()) {
            case STRING:
            case NUMBER:
                return in.nextString();
            case BOOLEAN:
                // 对于布尔类型比较特殊，需要做针对性处理
                return Boolean.toString(in.nextBoolean());
            case NULL:
                in.nextNull();
                return null;
            default:
                in.skipValue();
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
    }
}