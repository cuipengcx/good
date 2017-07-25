package com.jk.util;

import com.jk.model.User;
import com.xiaoleilu.hutool.json.JSONUtil;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Shiro工具类
 * @author cuip
 */
@Slf4j
public class ShiroUtils {

	private ShiroUtils(){}

	public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static User getUserEntity() {
		return (User)SecurityUtils.getSubject().getPrincipal();
	}

	public static Long getUserId() {
		return getUserEntity().getId();
	}
	
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	public static String getKaptcha(String key) {
		String kaptcha = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}


	/**
	 * 响应json数据
	 * @param response
	 * @param resultMap
	 */
	public static void writeJson(ServletResponse response, Map<String, Object> resultMap){
		try {
			response.setContentType("application/json;charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			@Cleanup PrintWriter writer = response.getWriter();
			writer.print(JSONUtil.parseObj(resultMap));
		} catch (IOException e) {
			log.error("响应json数据失败，失败信息:{}", e);
		}

	}
}