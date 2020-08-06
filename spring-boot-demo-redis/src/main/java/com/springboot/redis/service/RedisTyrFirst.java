package com.springboot.redis.service;

public interface RedisTyrFirst {
    public boolean setKey(String key, Object value);

    public Object getKey(String key);

    public void setKeyTime(String key,long keyTime);
}
