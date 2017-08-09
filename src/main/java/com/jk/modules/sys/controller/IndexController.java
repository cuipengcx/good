package com.jk.modules.sys.controller;

import com.jk.common.base.controller.BaseController;
import com.jk.common.util.ShiroUtils;
import com.jk.modules.sys.model.Permission;
import com.jk.modules.sys.model.User;
import com.jk.modules.sys.service.PermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页
 * Created by JK on 2017/1/22.
 */
@Controller
public class IndexController extends BaseController{

    private static final String BASE_PATH = "admin/";

    @Resource
    private PermissionService permissionService;

    /*
     * @methodName: toIndex
     * @param: []
     * @description: 定义welcome-file-list页面
     * @return: java.lang.String
     * @author: cuiP
     * @date: 2017/8/5 18:48
     * @version: V1.0.0
     */
    @GetMapping(value = "")
    public String toIndex(){
        return "redirect:/admin/index";
    }

    /**
     * 首页
     * @return
     */
    @GetMapping(value = "/admin/index")
    public String index(ModelMap modelMap){
        //从shiro的session中取user
        User user = ShiroUtils.getUserEntity();

        List<Permission> menuList = permissionService.findMenuListByUserId(user.getId());
        //通过model传到页面
        modelMap.addAttribute("menuList", menuList);
        log.info("------进入首页-------");
        return BASE_PATH+"index";
    }

    /**
     * 欢迎页
     * @return
     */
    @GetMapping(value = "/admin/welcome")
    public String welcome(){
        log.info("------进入欢迎页-------");
        return BASE_PATH+"welcome";
    }

    /**
     * 未授权页面
     * @return
     */
    @GetMapping(value = "/admin/403")
    public String unauthorized(){
        log.info("------没有权限-------");
        return BASE_PATH+"common/403";
    }
}
