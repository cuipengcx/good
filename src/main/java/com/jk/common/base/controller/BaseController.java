package com.jk.common.base.controller;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

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
}
