package com.jk.modules.job.model;

import com.jk.common.base.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;

/**
 * 任务调度
 * 
 * @author cuiP
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"cron"})
public class ScheduleJob extends BaseEntity {

	/** 任务调度的参数key */
	public static final String JOB_PARAM_KEY    = "jobParam";

	/**
	 * 任务名称
     */
	private String jobName;

	/**
	 * 任务分组
     */
	private String jobGroup;

	/**
	 * 执行计划
     */
	private String cron;

	/**
	 * 调度方式 false 远程 true 本地
     */
	private Boolean isLocal;

	/**
	 * 远程请求方式 只支持POST
     */
	private String remoteRequestMethod;

	/**
	 * 远程执行url
     */
	private String remoteUrl;

	/**
	 * 执行类名称 包名+类名
     */
	private String beanClass;

	/**
	 * 执行方法名称
	 */
	private String methodName;

	/**
	 * 参数
	 */
	private String params;

	/**
	 * 是否异步  0否 1是
     */
	private Boolean isAsync;

	/**
	 * 任务状态 0禁用 1启用
     */
	private Integer status;

	/**
	 * 描述
     */
	private String remarks;

	/**
	 * 创建者
     */
	private Long createBy;

	/**
	 * 修改者
     */
	private Long modifyBy;

	/**
	 * 创建者姓名
     */
	@Transient
	private String createByName;

	/**
	 * 修改者姓名
	 */
	@Transient
	private String modifyByName;

}
