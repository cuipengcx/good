package com.jk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 任务调度
 * 
 * @author cuiP
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ScheduleJob extends BaseEntity {

	public static final String STATUS_RUNNING = "1";
	public static final String STATUS_NOT_RUNNING = "0";

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
	 *  cron表达式
     */
	private String cron;

	/**
	 * 执行类名称 包名+类名
     */
	private String beanClass;

	/**
	 * 执行方法名称
	 */
	private String methodName;

	/**
	 * 触发器
     */
	private String jobTrigger;

	/**
	 * 参数
	 */
	private String params;

	/**
	 * 是否异步  0否 1是
     */
	private Boolean isSync;

	/**
	 * 任务状态 0禁用 1启用
     */
	private String status;

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
}
