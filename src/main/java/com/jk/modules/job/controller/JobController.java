package com.jk.modules.job.controller;

import com.github.pagehelper.PageInfo;
import com.jk.common.annotation.OperationLog;
import com.jk.common.base.controller.BaseController;
import com.jk.common.security.token.FormToken;
import com.jk.common.security.xss.XssHttpServletRequestWrapper;
import com.jk.modules.job.model.ScheduleJob;
import com.jk.modules.job.service.ScheduleJobService;
import com.jk.modules.sys.model.User;
import com.jk.modules.sys.service.UserService;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    @Resource
    private UserService userService;

    /**
     * 分页查询调度任务列表
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
        log.debug("分页查询调度任务列表参数! pageNum = {}, username = {}, username = {}, startTime = {}, endTime = {}", pageNum, jobName, startTime, endTime);
        PageInfo<ScheduleJob> pageInfo = scheduleJobService.findPage(pageNum, PAGESIZE, jobName, startTime, endTime);
        log.info("分页查询调度任务列表结果！ pageInfo = {}", pageInfo);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("jobName", jobName);
        modelMap.put("startTime", startTime);
        modelMap.put("endTime", endTime);
        return BASE_PATH + "job-list";
    }

    /**
     * 跳转到调度任务添加页面
     * @return
     */
    @FormToken(save = true)
    @RequiresPermissions("job:create")
    @GetMapping(value = "/add")
    public String add(ModelMap modelMap){
        log.info("跳转到调度任务添加页面!");
        return BASE_PATH + "job-add";
    }

    /**
     * 添加调度任务
     *
     * @param scheduleJob
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "添加调度任务")
    @RequiresPermissions("job:create")
    @ResponseBody
    @PostMapping
    public ModelMap saveJob(HttpServletRequest request, ScheduleJob scheduleJob) {

        //获取不进行xss过滤的request对象
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);

        ModelMap messagesMap = new ModelMap();

        log.debug("添加调度任务参数! scheduleJob = {}", scheduleJob);

        //判断任务是否已经存在相同的jobName和jobGroup
        ScheduleJob record = scheduleJobService.findByJobNameAndJobGroup(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        if (null != record) {
            messagesMap.put("status", FAILURE);
            messagesMap.put("message", "该调度任务已经被注册!");
            return messagesMap;
        }

        scheduleJob.setRemoteUrl(orgRequest.getParameter("remoteUrl"));
        scheduleJobService.saveScheduleJob(scheduleJob);

        log.info("添加调度任务成功! jobId = {}", scheduleJob.getId());
        messagesMap.put("status", SUCCESS);
        messagesMap.put("message", "添加成功!");
        return messagesMap;
    }

    /**
     * 跳转到调度任务编辑页面
     * @return
     */
    @FormToken(save = true)
    @RequiresPermissions("job:update")
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap modelMap) throws Exception {
        log.debug("跳转到编辑调度任务页面参数! id = {}", id);

        ScheduleJob model = scheduleJobService.findById(id);

        log.info("跳转到编辑调度任务信息页面成功!, id = {}", id);
        modelMap.put("model", model);
        return BASE_PATH + "job-edit";
    }

    /**
     * 更新调度任务信息
     * @param id
     * @param scheduleJob
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "编辑调度任务")
    @RequiresPermissions("job:update")
    @ResponseBody
    @PutMapping(value = "/{id}")
    public ModelMap updateJob(HttpServletRequest request, @PathVariable("id") Long id, ScheduleJob scheduleJob){

        //获取不进行xss过滤的request对象
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);

        ModelMap messagesMap = new ModelMap();

        log.debug("编辑调度任务参数! id= {}, scheduleJob = {}", id, scheduleJob);

        if(null == id){
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","ID不能为空!");
            return messagesMap;
        }

        scheduleJob.setId(id);
        scheduleJob.setRemoteUrl(orgRequest.getParameter("remoteUrl"));
        scheduleJobService.updateScheduleJob(scheduleJob);

        log.info("编辑调度任务成功! id= {}, scheduleJob = {}", id, scheduleJob);
        messagesMap.put("status",SUCCESS);
        messagesMap.put("message","编辑成功!");
        return messagesMap;
    }


    /**
     * 根据主键ID删除调度任务
     * @param id
     * @return
     */
    @OperationLog(value = "删除调度任务")
    @RequiresPermissions("job:delete")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {

        log.debug("删除调度任务! id = {}", id);

        if (null == id) {
            log.info("删除调度任务不存在! id = {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("删除调度任务不存在!");
        }

        scheduleJobService.deleteScheduleJob(id);

        log.info("删除调度任务成功! id = {}", id);
        return ResponseEntity.ok("删除成功!");
    }

    /**
     * 暂停调度任务
     * @param id
     * @return
     */
    @OperationLog(value = "暂停调度任务")
    @RequiresPermissions("job:pause")
    @PutMapping(value = "/pause/{id}")
    public ResponseEntity<String> pauseJob(@PathVariable("id") Long id) {

        log.debug("暂停调度任务! id = {}", id);

        if (null == id) {
            log.info("暂停调度任务不存在! id = {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂停调度任务不存在!");
        }

        scheduleJobService.pauseJob(id);

        log.info("暂停调度任务成功! id = {}", id);
        return ResponseEntity.ok("暂停成功!");
    }

    /**
     * 恢复调度任务
     * @param id
     * @return
     */
    @OperationLog(value = "恢复调度任务")
    @RequiresPermissions("job:resume")
    @PutMapping(value = "/resume/{id}")
    public ResponseEntity<String> resumeJob(@PathVariable("id") Long id) {

        log.debug("恢复调度任务! id = {}", id);

        if (null == id) {
            log.info("恢复调度任务不存在! id = {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("恢复调度任务不存在!");
        }

        scheduleJobService.resumeJob(id);

        log.info("恢复调度任务成功! id = {}", id);
        return ResponseEntity.ok("恢复成功!");
    }

    /**
     * 运行一次调度任务
     * @param id
     * @return
     */
    @OperationLog(value = "运行一次调度任务")
    @RequiresPermissions("job:run")
    @PutMapping(value = "/run/{id}")
    public ResponseEntity<String> runOnce(@PathVariable("id") Long id) {

        log.debug("运行一次调度任务! id = {}", id);

        if (null == id) {
            log.info("运行一次调度任务不存在! id = {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("运行一次调度任务不存在!");
        }

        scheduleJobService.runOnce(id);

        log.info("运行一次调度任务成功! id = {}", id);
        return ResponseEntity.ok("运行成功!");
    }

    /**
     * 查看调度任务详情信息
     * @param id
     * @param modelMap
     * @return
     */
    @RequiresPermissions("job:view")
    @GetMapping("/{id}")
    public String view(@PathVariable("id")Long id, ModelMap modelMap){
        ScheduleJob model = scheduleJobService.findById(id);
        if(null != model){
            //创建者
            User userCreate = userService.findById(model.getCreateBy());
            if(null != userCreate){
                model.setCreateByName(userCreate.getUsername());
            }
            //修改者
            User userModify = userService.findById(model.getModifyBy());
            if(null != userModify){
                model.setModifyByName(userModify.getUsername());
            }
        }
        modelMap.put("model", model);
        return BASE_PATH + "job-view";
    }

    /**
     * 检验任务是否存在
     * @param jobName
     * @param jobGroup
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/isExist")
    public Boolean isExist(Long id, String jobName, String jobGroup) throws Exception {
        boolean flag = true;
        log.debug("检验任务是否存在参数! id= {}, jobName= {}, jobGroup= {}", id, jobName, jobGroup);
        if(StrUtil.isNotEmpty(jobName) && StrUtil.isNotEmpty(jobGroup)){
            ScheduleJob record = scheduleJobService.findByJobNameAndJobGroup(jobName, jobGroup);
            if (null != record && !record.getId().equals(id)) {
                flag = false;
            }
        }
        log.info("检验任务是否存在结果! flag = {}", flag);
        return flag;
    }

}
