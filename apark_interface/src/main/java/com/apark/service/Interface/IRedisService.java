package com.apark.service.Interface;

import java.util.List;

public interface IRedisService {
    public boolean set(String key, String value);

    public String get(final String key);

    public boolean expire(String key, long expire);

    public <T> boolean setList(String key, List<T> list);

    public <T> List<T> getList(String key, Class<T> clz);

    public long lpush(String key, Object obj);

    public long rpush(String key, Object obj);

    public String lpop(String key);

    public boolean set(final String key, final String value, long expire);

    public boolean set(final String key, final Object value, long expire);

    public Object getKey(final String key);
}
