package com.jk.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jk.controller.BaseController;
import com.jk.model.GoodUser;
import com.jk.service.GoodUserService;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 前台用户管理
 * @author cuiP
 * Created by JK on 2017/2/21.
 */
@Controller
@RequestMapping("/admin/good")
public class GoodUserController extends BaseController{

    private static final String BASE_PATH = "admin/good/";

    @Resource
    private GoodUserService goodUserService;

    /**
     * 分页查询前台用户列表
     * @param pageNum  当前页码
     * @param username 用户名
     * @param startTime 注册开始时间
     * @param endTime   注册结束时间
     * @param modelMap
     * @return
     */
//    @RequiresPermissions("user:list")
    @GetMapping
    public String list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            String username, String startTime, String endTime, ModelMap modelMap) {
        try {
            log.debug("分页查询前台用户列表参数! pageNum = {}, username = {}, username = {}, startTime = {}, endTime = {}", pageNum, username, startTime, endTime);
            PageInfo<GoodUser> pageInfo = goodUserService.findPage(pageNum, PAGESIZE, username, startTime, endTime, false);
            log.info("分页查询前台用户列表结果！ pageInfo = {}", pageInfo);
            modelMap.put("pageInfo", pageInfo);
            modelMap.put("username", username);
            modelMap.put("startTime", startTime);
            modelMap.put("endTime", endTime);
        } catch (Exception e) {
            log.error("分页查询前台用户列表失败! e = {}", e);
        }
        return BASE_PATH + "user-list";
    }


    /**
     * 禁用|启用用户
     * @param id
     * @return
     */
//    @RequiresPermissions("user:status")
    @PutMapping(value = "/status/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable("id") Long id){
        try {
            log.debug("禁用|启用用户参数! id = {}", id);
            GoodUser goodUser = goodUserService.findById(id);

            if (null == goodUser) {
                log.info("用户不存在! id = {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("用户不存在!");
            }

            //禁用启用
            goodUser.setIsLock(!goodUser.getIsLock());
            goodUserService.updateSelective(goodUser);

            log.info("禁用|启用用户成功! id = {}", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error("禁用|启用用户失败! id = {}, e = {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 根据主键ID删除用户
     *
     * @param id
     * @return
     */
//    @RequiresPermissions("user:delete")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            log.debug("删除用户! id = {}", id);

            GoodUser goodUser = goodUserService.findById(id);
            if (null == goodUser) {
                log.info("删除用户不存在! id = {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("用户不存在!");
            }

            goodUser.setIsDel(true);
            goodUserService.updateSelective(goodUser);
            log.info("删除用户成功! id = {}", id);

            return ResponseEntity.ok("已删除!");
        } catch (Exception e) {
            log.error("删除用户失败! id = {}, e = {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 查看用户详情
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/{id}")
    public String view(@PathVariable("id") Long id, ModelMap modelMap){
        GoodUser goodUser = goodUserService.findById(id);
        log.info("查看用户详情！id = {}", id);
        modelMap.put("model", goodUser);
        return BASE_PATH+"user-view";
    }

    /**
     * 跳转到修改密码页面
     * @param id
     * @return
     */
    @GetMapping(value = "/change-password/{id}")
    public String changPassWord(@PathVariable("id") Long id, ModelMap modelMap){
        GoodUser user = goodUserService.findById(id);
        modelMap.put("model", user);
        return BASE_PATH+"change-password";
    }

    /**
     * 修改用户密码
     * @param id
     * @return
     */
    @ResponseBody
    @PutMapping(value = "/update-password/{id}")
    public ModelMap updatePassWord(@PathVariable("id") Long id, String password){
        ModelMap messagesMap = new ModelMap();
        try {
            log.debug("修改用户密码参数! id= {}", id);
            if(null == id){
                messagesMap.put("status",FAILURE);
                messagesMap.put("message","ID不能为空!");
                return messagesMap;
            }
            GoodUser goodUser = new GoodUser();
            goodUser.setId(id);
            goodUser.setPassword(SecureUtil.md5().digestHex(password));
            goodUserService.updateSelective(goodUser);

            log.info("修改用户成功! id= {}", id);
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","修改成功!");
            return messagesMap;
        } catch (Exception e) {
            log.error("修改用户失败! id = {}, e = {}", id, e);
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","修改失败!");
            return messagesMap;
        }
    }
}
