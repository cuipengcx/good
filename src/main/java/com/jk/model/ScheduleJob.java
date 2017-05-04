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
	public static final String CONCURRENT_IS = "1";
	public static final String CONCURRENT_NOT = "0";

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
	 * 任务执行时调用哪个类的方法 包名+类名
     */
	private String beanClass;
	/**
	 * 是否同步  0否 1是
     */
	private String isConcurrent;
	/**
	 * 任务调用的方法名
     */
	private String methodName = "run";
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
