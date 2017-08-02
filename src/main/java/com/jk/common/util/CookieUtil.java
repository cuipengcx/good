package com.jk.common.util;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cuiP
 * Created by JK on 2017/3/21.
 */
public class CookieUtil {

    /**
     * 添加cookie
     * @param httpServletResponse
     * @param name cookie的key
     * @param value cookie的value
     * @param secure 表示这个cookie仅在https环境下才能使用
     * @param maxAge 最长存活时间 单位为秒
     * @param domain
     */
    public static void create(HttpServletResponse httpServletResponse, String name, String value, Boolean secure, Integer maxAge, String domain, String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(secure);
        cookie.setMaxAge(maxAge);
        if(domain!=null){
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        httpServletResponse.addCookie(cookie);
    }

    /**
     * 往根下面存一个cookie
     * * @param name cookie的key
     * @param value cookie的value
     * @param value secure 表示这个cookie仅在https环境下才能使用
     * @param domain domain
     * @param maxAge  最长存活时间 单位为秒
     * @param response
     */
    public static void create(HttpServletResponse response, String name ,String value, Boolean secure, Integer maxAge, String domain){
        create(response, name, value, secure, maxAge, domain, "/");
    }

    /**
     * 清空所有cookie
     * @param httpServletResponse
     * @param name
     */
    public static void clear(HttpServletResponse httpServletResponse, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
    }

    public static String getValue(HttpServletRequest httpServletRequest, String name) {
        Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
        return cookie != null ? cookie.getValue() : null;
    }

    /**
     * 刷新cookie
     * @param request
     * @param response
     * @param name
     * @param domain
     * @param maxAge
     */
    public static void refreshSessionCookie(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                            String name, String domain, int maxAge) {
        Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
        if (cookie != null) {
            cookie.setMaxAge(maxAge);
            cookie.setDomain(domain);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
        }
    }
}
