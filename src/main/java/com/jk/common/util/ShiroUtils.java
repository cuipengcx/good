package com.jk.common.util;

import com.jk.modules.sys.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 * @author cuip
 */
@Slf4j
public class ShiroUtils {

	private ShiroUtils(){}


	/**
	 * 获取当前主体
	 * @return
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取当前用户session
	 * @return
     */
	public static Session getSession() {
        return getSubject().getSession();
	}


	/**
	 * 获取当前登录对象
	 * @return
     */
	public static User getUserEntity() {
		Object subject = getSubject().getPrincipal();
		if(null == subject){
			return new User();
		}
		return (User)subject;
	}

	/**
	 * 获取当前登录用户的用户名
	 * @return
     */
	public static String getUserName() {
		return getUserEntity().getUsername();
	}

	/**
	 * 获取当前登录用户的用户ID
	 * @return
     */
	public static Long getUserId() {
		return getUserEntity().getId();
	}

	/**
	 * 当前登录用户session赋值
	 * @param key
	 * @param value
     */
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * 当前登录用户session取值
	 * @param key
	 * @return
     */
	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	/**
	 * 判断是否登录
	 * @return
     */
	public static boolean isLogin() {
		return getSubject().getPrincipal() != null && getSubject().isAuthenticated();
	}

	/**
	 * 执行退出
     */
	public static void logout() {
		//清除认证缓存(由于认证时放入的是整个对象的信息,subject.logout()无法清除认证信息，所以手动清除)
		EhCacheUtils.remove("goodAuthenticationCache", ShiroUtils.getUserName());
		getSubject().logout();
	}

	/**
	 * 获取验证码，获取一次后删除
	 * @param key
	 * @return
     */
	public static String getKaptcha(String key) {
		String kaptcha = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}
}