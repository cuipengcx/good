package com.jk.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jk.controller.BaseController;
import com.jk.model.AuthenticationInfo;
import com.jk.model.User;
import com.jk.service.AuthenticationInfoService;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 认证管理
 * Created by JK on 2017/2/22.
 */
@Controller
@RequestMapping("/admin/")
public class AuthenticationInfoController extends BaseController{

    private static final String BASE_PATH = "admin/good/";

    @Resource
    private AuthenticationInfoService authenticationInfoService;

    /**
     * 分页查询待审核的权益认证列表
     * @param pageNum  当前页码
     * @param type     认证类型
     * @param username 用户名/机构名
     * @param startTime 申请开始时间
     * @param endTime   申请结束时间
     * @param modelMap
     * @return
     */
    @GetMapping(value = "authentication-info")
    public String list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            Integer type,
            Integer checkStatus,
            String username, String startTime, String endTime, ModelMap modelMap){
        try {
            log.debug("分页查询待审核的权益认证列表参数! pageNum = {}, username = {}, type = {}, checkStatus = {}, username = {}, startTime = {}, endTime = {}", pageNum, type, checkStatus, username, startTime, endTime);
            PageInfo<AuthenticationInfo> pageInfo = authenticationInfoService.findPage(pageNum, PAGESIZE, type, username, checkStatus, startTime, endTime);
            log.info("分页查询待审核的权益认证列表结果！ pageInfo = {}", pageInfo);

            modelMap.put("pageInfo", pageInfo);
            modelMap.put("username", username);
            modelMap.put("startTime", startTime);
            modelMap.put("endTime", endTime);
            modelMap.put("type", type);
            modelMap.put("checkStatus", checkStatus);
        } catch (Exception e) {
            log.error("分页查询待审核的权益认证列表失败! e = {}", e);
        }
        return BASE_PATH+"authentication-info";
    }

    /**
     * 管理员审核用户认证
     * @param id
     * @return
     */
    @PutMapping(value = "/authentication-info-check/{id}")
    public ResponseEntity<String> viewCheck(@PathVariable("id") Long id, Boolean isAccess){
        try {
            log.debug("管理员审核用户认证参数！id = {}", id);
            if(null == id){
                log.info("管理员审核用户认证, ID不能为空! id = {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("管理员审核用户认证, ID不能为空!");
            }
            AuthenticationInfo authenticationInfo = new  AuthenticationInfo();
            authenticationInfo.setId(id);
            authenticationInfo.setCheckTime(new Date());
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            authenticationInfo.setAdminId(user.getId());
            authenticationInfo.setAdminName(user.getUsername());
            if(isAccess){
                authenticationInfo.setCheckStatus(1);
            }else {
                authenticationInfo.setCheckStatus(2);
            }
            authenticationInfoService.updateSelective(authenticationInfo);
            if(isAccess){
                return ResponseEntity.ok("审核已通过!");
            }else {
                return ResponseEntity.ok("审核已拒绝!");
            }
        } catch (Exception e) {
            log.error("管理员审核用户认证失败! id = {}, e = {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 查看详情
     * @param id
     * @return
     */
    @GetMapping(value = "/authentication-info-view/{id}")
    public String view(@PathVariable("id") Long id, ModelMap modelMap){
        log.info("跳转到查看详情页面！id = {}", id);
        AuthenticationInfo authenticationInfo = authenticationInfoService.findById(id);
        modelMap.put("model", authenticationInfo);
        return BASE_PATH+"authentication-info-view";
    }
}
