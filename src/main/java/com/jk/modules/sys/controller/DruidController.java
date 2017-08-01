package com.jk.modules.sys.controller;

import com.jk.common.base.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @packageName: com.jk.controller.admin
 * @description: SQL监控
 * @author: cuiP
 * @date: 2017/7/27 23:22
 * @version: V1.0.0
 */
@Controller
@RequestMapping("/admin/monitor/druid")
public class DruidController extends BaseController{

    private static final String BASE_PATH = "admin/manager/";

    /**
     * @methodName: view
     * @param: []
     * @description: druid监控列表
     * @return: java.lang.String
     * @author: cuiP
     * @date: 2017/7/27 23:25 
     * @version: V1.0.0
     */
    @RequiresPermissions("druid:list")
    @GetMapping
    public String list(){
        return BASE_PATH + "admin-monitor-druid";
    }

}
