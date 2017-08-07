package com.jk.common.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import java.util.List;

/**
 *
 * Cache工具类
 */
public class EhCacheUtils {

    private static EhCacheCacheManager ehCacheCacheManager = ((EhCacheCacheManager) SpringUtils.getBean("ehCacheCacheManager"));

    private static final String DEFAULT_CACHE = "defaultCache";


    /**
     * 根据节点名称为cacheName和键为key获取一条数据
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key) {
        Element element = getCache(cacheName).get(key);
        return element==null?null:element.getObjectValue();
    }

    /**
     * 获取默认节点下键为key的一条数据
     * @param key
     * @return
     */
    public static Object get(String key){
        return get(DEFAULT_CACHE, key);
    }

    /**
     * 返回节点cacheName下所有的key
     * @param cacheName
     * @return
     */
    public static List getKeys(String cacheName){
        return getCache(cacheName).getKeys();
    }

    /**
     * 返回节点默认节点下所有的key
     * @return
     */
    public static List getKeys(){
        return getCache(DEFAULT_CACHE).getKeys();
    }

    /**
     * 往该节点cacheName下添加一条缓存数据
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value) {
        Element element = new Element(key, value);
        getCache(cacheName).put(element);
    }

    /**
     * 默认节点添加一条缓存数据
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        put(DEFAULT_CACHE, key, value);
    }

    /**
     * 移除节点cacheName下键为key的一条缓存数据
     * @param cacheName
     * @param key
     */
    public static void remove(String cacheName, String key) {
        getCache(cacheName).remove(key);
    }

    /**
     * 移除默认节点下键为key的一条缓存数据
     * @param key
     */
    public static void remove(String key) {
        remove(DEFAULT_CACHE, key);
    }

    /**
     * 移除节点cacheName下所有的键值对
     * @param cacheName
     */
    public static void removeAll(String cacheName) {
        getCache(cacheName).removeAll();
    }

    /**
     * 移除默认节点下所有的键值对
     */
    public static void removeAll() {
        removeAll(DEFAULT_CACHE);
    }

    /**
     * 移除节点cacheName下键为keys的数据
     * @param cacheName
     * @param keys
     */
    public static void removeAll(String cacheName, List keys) {
        getCache(cacheName).removeAll(keys);
    }

    /**
     * 移除默认节点下键为keys的数据
     * @param keys
     */
    public static void removeAll(List keys) {
        removeAll(DEFAULT_CACHE, keys);
    }

    /**
     * 返回一个缓存对象
     * @param cacheName
     * @return
     */
    private static Cache getCache(String cacheName){
        Cache cache = getCacheManager().getCache(cacheName);
        if (cache == null){
            getCacheManager().addCache(cacheName);
            cache = getCacheManager().getCache(cacheName);
        }
        return cache;
    }

    private static CacheManager getCacheManager() {
        return ehCacheCacheManager.getCacheManager();
    }
}