package com.apark.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class JSONUtil<T> {

    public static String toJson(Object obj) {
        return JSONObject.toJSON(obj).toString();
    }

    public static <T> List<T> toList(String json, Class<T> clz) {
        return JSONArray.parseArray(json, clz);
    }

    public <T> List<T> getList(String json, Class<T> clz) {
        return JSONArray.parseArray(json, clz);
    }

}
