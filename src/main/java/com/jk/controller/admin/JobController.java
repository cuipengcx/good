package com.jk.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jk.annotation.OperationLog;
import com.jk.controller.BaseController;
import com.jk.model.ScheduleJob;
import com.jk.service.ScheduleJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
     * @param jobName  用户名
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
        log.debug("分页查询任务调度列表参数! pageNum = {}, username = {}, username = {}, startTime = {}, endTime = {}", pageNum, jobName, startTime, endTime);
        PageInfo<ScheduleJob> pageInfo = scheduleJobService.findPage(pageNum, PAGESIZE, jobName, startTime, endTime);
        log.info("分页查询任务调度列表结果！ pageInfo = {}", pageInfo);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("jobName", jobName);
        modelMap.put("startTime", startTime);
        modelMap.put("endTime", endTime);
        return BASE_PATH + "job-list";
    }

    /**
     * 跳转到任务调度添加页面
     * @return
     */
    @RequiresPermissions("job:create")
    @GetMapping(value = "/add")
    public String add(ModelMap modelMap){
        log.info("跳转到任务调度添加页面!");
        return BASE_PATH + "job-add";
    }

    /**
     * 添加任务调度
     * @param scheduleJob
     * @return
     */
    @OperationLog(value = "添加任务调度")
    @RequiresPermissions("job:create")
    @ResponseBody
    @PostMapping
    public ModelMap saveJob(ScheduleJob scheduleJob){
        ModelMap messagesMap = new ModelMap();

        log.debug("添加任务调度参数! scheduleJob = {}", scheduleJob);

        scheduleJobService.save(scheduleJob);

        log.info("添加任务调度成功! jobId = {}", scheduleJob.getId());
        messagesMap.put("status",SUCCESS);
        messagesMap.put("message","添加成功!");
        return messagesMap;
    }
}
