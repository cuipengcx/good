package com.jk.common.base.controller;

import com.jk.common.DateEditor;
import com.jk.common.StringEditor;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;

public class BaseController {

	protected final transient Log log = LogFactory.get(this.getClass());

	protected static final String FAILURE = "failure";
	protected static final String SUCCESS = "success";

	/**
	 * 默认页为1
     */
	protected static final Integer PAGENUM = 1;
	/**
	 * 页码大小10
     */
	protected static final Integer PAGESIZE = 10;

	/**
	 * ajax
	 * 提示常量
	 */
	protected static final String SUCCESS_LOAD_MESSAGE = "加载成功!";
	protected static final String FAILURE_LOAD_MESSAGE = "加载失败!";
	/**
	 * 保存
	 * 提示常量
	 */
	protected static final String SUCCESS_SAVE_MESSAGE = "保存成功!";
	protected static final String FAILURE_SAVE_MESSAGE = "保存失败!";
	/**
	 * 更新
	 * 提示常量
	 */
	protected static final String SUCCESS_UPDATE_MESSAGE = "更新成功!";
	protected static final String FAILURE_UPDATE_MESSAGE = "更新失败!";
	/**
	 * 充值
	 * 提示常量
	 */
	protected static final String SUCCESS_CREDIT_MESSAGE = "充值成功!";
	protected static final String FAILURE_CREDIT_MESSAGE = "充值失败!";
	/**
	 * 删除
	 * 提示常量
	 */
	protected static final String SUCCESS_DELETE_MESSAGE = "删除成功!";
	protected static final String FAILURE_DELETE_MESSAGE = "删除失败!";
	protected static final String WARNING_DELETE_MESSAGE = "已经删除!";
	/**
	 * 禁用启用
	 */
	protected static final String SUCCESS_ENABLE_TRUE = "启用成功!";
	protected static final String SUCCESS_ENABLE_FALSE = "禁用成功!";
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, new DateEditor(true));
		binder.registerCustomEditor(String.class, "password", new StringEditor(true));
	}


//	/**
//	 * 生成api执行结果的通用方法
//	 *
//	 * @param <T> - 结果类型
//	 * @param code - api执行结果代码，不能为空
//	 * @param msg - 结果描述
//	 * @param res - 结果数据，可以为空
//	 * @return
//	 */
//	public <T> DataResult<T> makeResult(int code, String msg, T res) {
//		return new DataResult<>(code, msg, res);
//	}
//
//	public <T> DataResult<T> makeResult(ExecStatus status, T res) {
//		return new DataResult<>(status.getCode(), status.getMsg(), res);
//	}
//
//	public <T> DataResult<T> makeResult(ExecStatus status, String detail) {
//		return new DataResult<>(status.getCode(), status.getMsg() + "：" + detail, null);
//	}
//
//	public <T> DataResult<T> success(String msg, T res) {
//		return this.makeResult(ExecStatus.SUCCESS.getCode(), msg, res);
//	}
//
//	public <T> DataResult<T> success(T res) {
//		return this.makeResult(ExecStatus.SUCCESS, res);
//	}
//
//	/**
//	 *
//	 * @param error
//	 * @return
//	 */
//	public <T> DataResult<T> fail(ExecStatus error) {
//		return this.makeResult(error, null);
//	}
//
//	public <T> DataResult<T> fail(ExecStatus error, String detail) {
//		return this.makeResult(error, detail);
//	}
//
//	public <T> DataResult<T> fail(int code, String msg) {
//		return this.makeResult(code, msg, null);
//	}
//
//	public <T> DataResult<T> fail(int code, String msg,T res) {
//		return this.makeResult(code, msg, res);
//	}
//
//	public <T> DataResult<T> fail(String msg) {
//		return this.makeResult(ExecStatus.FAIL.getCode(), msg, null);
//	}
//
//	public <T> DataResult<T> fail() {
//		return this.makeResult(ExecStatus.FAIL, "系统繁忙，请稍后重试！");
//	}

}
