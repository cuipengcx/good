package com.jk.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jk.controller.BaseController;
import com.jk.model.ScheduleJob;
import com.jk.service.ScheduleJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author cuiP
 * Created by JK on 2017/5/5.
 */
@Controller
@RequestMapping("/admin/job")
public class JobController extends BaseController{

    private static final String BASE_PATH = "admin/job/";

    @Resource
    private ScheduleJobService scheduleJobService;

    /**
     * 分页查询任务调度列表
     *
     * @param pageNum   当前页码
     * @param username  用户名
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param modelMap
     * @return
     */
    @RequiresPermissions("log:list")
    @GetMapping
    public String list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            String jobName, String startTime, String endTime, ModelMap modelMap) {
        try {
            log.debug("分页查询任务调度列表参数! pageNum = {}, username = {}, username = {}, startTime = {}, endTime = {}", pageNum, jobName, startTime, endTime);
            PageInfo<ScheduleJob> pageInfo = scheduleJobService.findPage(pageNum, PAGESIZE, jobName, startTime, endTime);
            log.info("分页查询任务调度列表结果！ pageInfo = {}", pageInfo);
            modelMap.put("pageInfo", pageInfo);
            modelMap.put("jobName", jobName);
            modelMap.put("startTime", startTime);
            modelMap.put("endTime", endTime);
        } catch (Exception e) {
            log.error("分页查询任务调度列表失败! e = {}", e);
        }
        return BASE_PATH + "job-list";
    }

}
