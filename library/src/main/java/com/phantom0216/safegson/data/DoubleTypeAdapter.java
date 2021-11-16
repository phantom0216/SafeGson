package com.phantom0216.safegson.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class DoubleTypeAdapter extends TypeAdapter<Double> {

    @Override
    public Double read(JsonReader in) throws IOException {
        switch (in.peek()) {
            case NUMBER:
                return in.nextDouble();
            case STRING:
                String result = in.nextString();
                if (result == null || "".equals(result)) {
                    return 0D;
                }
                return Double.parseDouble(result);
            case NULL:
                in.nextNull();
                return null;
            default:
                in.skipValue();
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void write(JsonWriter out, Double value) throws IOException {
        out.value(value);
    }
}