package com.phantom0216.safegson.element;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.phantom0216.safegson.GsonFactory;
import com.phantom0216.safegson.INotifyInterface;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public class CollectionTypeAdapter<E> extends TypeAdapter<Collection<E>> {

    private final TypeAdapter<E> mElementTypeAdapter;
    private final ObjectConstructor<? extends Collection<E>> mObjectConstructor;

    public CollectionTypeAdapter(Gson gson, Type elementType, TypeAdapter<E> elementTypeAdapter,
        ObjectConstructor<? extends Collection<E>> constructor) {
        mElementTypeAdapter =
            new TypeAdapterRuntimeTypeWrapper<>(gson, elementTypeAdapter, elementType);
        mObjectConstructor = constructor;
    }

    @Override
    public Collection<E> read(JsonReader in) throws IOException {
        JsonToken jsonToken = in.peek();

        if (jsonToken == JsonToken.NULL) {
            in.nextNull();
            return null;
        }

        if (jsonToken != JsonToken.BEGIN_ARRAY) {
            INotifyInterface callback = GsonFactory.getNotifyCallback();
            if (callback != null) {
                callback.onSkipParseError(in.toString());
            }
            in.skipValue();
            return null;
        }

        Collection<E> collection = mObjectConstructor.construct();
        in.beginArray();
        while (in.hasNext()) {
            E instance = mElementTypeAdapter.read(in);
            collection.add(instance);
        }
        in.endArray();
        return collection;
    }

    @Override
    public void write(JsonWriter out, Collection<E> collection) throws IOException {
        if (collection == null) {
            out.nullValue();
            return;
        }

        out.beginArray();
        for (E element : collection) {
            mElementTypeAdapter.write(out, element);
        }
        out.endArray();
    }
}