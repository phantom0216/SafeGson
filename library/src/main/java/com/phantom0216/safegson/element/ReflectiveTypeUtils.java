package com.phantom0216.safegson.element;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

public class ReflectiveTypeUtils {

    private final static ArrayList<Class<?>> TYPE_TOKENS = new ArrayList<>();

    static {
        // 添加 Gson 已适配的类型
        TYPE_TOKENS.add(String.class);
        TYPE_TOKENS.add(Integer.class);
        TYPE_TOKENS.add(Boolean.class);
        TYPE_TOKENS.add(Byte.class);
        TYPE_TOKENS.add(Short.class);
        TYPE_TOKENS.add(Long.class);
        TYPE_TOKENS.add(Double.class);
        TYPE_TOKENS.add(Float.class);
        TYPE_TOKENS.add(Number.class);
        TYPE_TOKENS.add(AtomicInteger.class);
        TYPE_TOKENS.add(AtomicBoolean.class);
        TYPE_TOKENS.add(AtomicLong.class);
        TYPE_TOKENS.add(AtomicLongArray.class);
        TYPE_TOKENS.add(AtomicIntegerArray.class);
        TYPE_TOKENS.add(Character.class);
        TYPE_TOKENS.add(StringBuilder.class);
        TYPE_TOKENS.add(StringBuffer.class);
        TYPE_TOKENS.add(BigDecimal.class);
        TYPE_TOKENS.add(BigInteger.class);
        TYPE_TOKENS.add(URL.class);
        TYPE_TOKENS.add(URI.class);
        TYPE_TOKENS.add(UUID.class);
        TYPE_TOKENS.add(Currency.class);
        TYPE_TOKENS.add(Locale.class);
        TYPE_TOKENS.add(InetAddress.class);
        TYPE_TOKENS.add(BitSet.class);
        TYPE_TOKENS.add(Date.class);
        TYPE_TOKENS.add(GregorianCalendar.class);
        TYPE_TOKENS.add(Calendar.class);
        TYPE_TOKENS.add(Time.class);
        TYPE_TOKENS.add(java.sql.Date.class);
        TYPE_TOKENS.add(Timestamp.class);
        TYPE_TOKENS.add(Class.class);
    }

    public static boolean containsClass(Class<?> clazz) {
        return TYPE_TOKENS.contains(clazz);
    }
}