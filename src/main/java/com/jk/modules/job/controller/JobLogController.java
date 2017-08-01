package com.jk.modules.job.controller;

import com.github.pagehelper.PageInfo;
import com.jk.common.base.controller.BaseController;
import com.jk.modules.job.model.ScheduleJobLog;
import com.jk.modules.job.service.ScheduleJobLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author cuiP
 * Created by JK on 2017/5/22.
 */
@Controller
@RequestMapping("/admin/job/log")
public class JobLogController extends BaseController{

    private static final String BASE_PATH = "admin/job/";

    @Resource
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 根据ID分页查询调度任务历史
     *
     * @param pageNum   当前页码
     * @param jobName  用户名
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param modelMap
     * @return
     */
    @RequiresPermissions("job:log")
    @GetMapping("/{jobId}")
    public String list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @PathVariable("jobId") Long jobId,
            String jobName, String startTime, String endTime, ModelMap modelMap) {
        log.debug("根据ID分页查询调度任务历史参数! pageNum = {}, username = {}, username = {}, startTime = {}, endTime = {}", pageNum, jobName, startTime, endTime);
        PageInfo<ScheduleJobLog> pageInfo = scheduleJobLogService.findPage(pageNum, PAGESIZE, jobId, jobName, startTime, endTime);
        log.info("根据ID分页查询调度任务历史结果！ pageInfo = {}", pageInfo);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("jobId", jobId);
        modelMap.put("jobName", jobName);
        modelMap.put("startTime", startTime);
        modelMap.put("endTime", endTime);
        return BASE_PATH + "job-log";
    }
}
