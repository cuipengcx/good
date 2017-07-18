package com.jk.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.cache.ehcache.EhCacheCacheManager;

/**
 * Cache工具类
 */
public class EhCacheUtils {

    private static EhCacheCacheManager ehCacheCacheManager = ((EhCacheCacheManager) SpringUtils.getBean("ehCacheCacheManager"));
      
    public static Object get(String cacheName, String key) {
        Element element = getCache(cacheName).get(key);
        return element==null?null:element.getObjectValue();
    }
  
    public static void put(String cacheName, String key, Object value) {
        Element element = new Element(key, value);
        getCache(cacheName).put(element);
    }
  
    public static void remove(String cacheName, String key) {
        getCache(cacheName).remove(key);
    }

    public static void removeAll(String cacheName) {
        getCache(cacheName).removeAll();
    }
      
    private static Cache getCache(String cacheName){
        Cache cache = getCacheManager().getCache(cacheName);
        if (cache == null){
            getCacheManager().addCache(cacheName);
            cache = getCacheManager().getCache(cacheName);
        }
        return cache;
    }

    public static CacheManager getCacheManager() {
        return ehCacheCacheManager.getCacheManager();
    }
}