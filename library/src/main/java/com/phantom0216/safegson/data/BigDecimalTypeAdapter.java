package com.phantom0216.safegson.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.math.BigDecimal;

public class BigDecimalTypeAdapter extends TypeAdapter<BigDecimal> {

    @Override
    public BigDecimal read(JsonReader in) throws IOException {
        switch (in.peek()) {
            case NUMBER:
            case STRING:
                String result = in.nextString();
                if (result == null || "".equals(result)) {
                    return new BigDecimal(0);
                }
                return new BigDecimal(result);
            case NULL:
                in.nextNull();
                return null;
            default:
                in.skipValue();
                return null;
        }
    }

    @Override
    public void write(JsonWriter out, BigDecimal value) throws IOException {
        out.value(value);
    }
}