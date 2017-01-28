package com.shivamdev.transactionviewer.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by shivam on 28/1/17.
 */

public final class GsonUtil {

    private static GsonUtil gsonUtil;
    private final Gson gson;

    private GsonUtil() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    public static synchronized GsonUtil getInstance() {
        if (gsonUtil == null) {
            gsonUtil = new GsonUtil();
        }
        return gsonUtil;
    }

    public String toJson(Object object) {
        return gson.toJson(object);
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}