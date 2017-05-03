package com.jk.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jk.annotation.OperationLog;
import com.jk.controller.BaseController;
import com.jk.model.Role;
import com.jk.model.User;
import com.jk.service.RoleService;
import com.jk.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/1/19.
 */
@Controller
@RequestMapping("/admin/manager")
public class UserController extends BaseController {

    private static final String BASE_PATH = "admin/manager/";

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    /**
     * 分页查询管理员列表
     *
     * @param pageNum   当前页码
     * @param username  用户名
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param modelMap
     * @return
     */
    @OperationLog(value = "分页查询管理员列表")
    @RequiresPermissions("user:list")
    @GetMapping(value = "/user")
    public String list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            String username, String startTime, String endTime, ModelMap modelMap) {
        try {
            log.debug("分页查询管理员列表参数! pageNum = {}, username = {}, username = {}, startTime = {}, endTime = {}", pageNum, username, startTime, endTime);
            PageInfo<User> pageInfo = userService.findPage(pageNum, PAGESIZE, username, startTime, endTime);
            log.info("分页查询管理员列表结果！ pageInfo = {}", pageInfo);
            modelMap.put("pageInfo", pageInfo);
            modelMap.put("username", username);
            modelMap.put("startTime", startTime);
            modelMap.put("endTime", endTime);
        } catch (Exception e) {
            log.error("分页查询管理员列表失败! e = {}", e);
        }
        return BASE_PATH + "admin-list";
    }

    /**
     * 根据主键ID删除管理员
     *
     * @param id
     * @return
     */
    @OperationLog(value = "根据主键ID删除管理员")
    @RequiresPermissions("user:delete")
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            log.debug("删除管理员! id = {}", id);

            User user = userService.findById(id);
            if (null == user) {
                log.info("删除管理员不存在! id = {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("管理员不存在!");
            }

            if(user.getIsAdmin()){
                log.info("禁用|启用管理员失败, 超级管理员不允许操作! id = {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("超级管理员不允许操作!");
            }

            userService.deleteById(id);
            log.info("删除管理员成功! id = {}", id);

            return ResponseEntity.ok("已删除!");
        } catch (Exception e) {
            log.error("删除管理员失败! id = {}, e = {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 批量删除管理员
     * @param ids
     * @return
     */
    @OperationLog(value = "批量删除管理员")
    @DeleteMapping(value = "/user/batch/{ids}")
    public ResponseEntity<Void> deleteBatch(@PathVariable("ids") List<Object> ids) {
        try {
            log.debug("批量删除管理员! ids = {}", ids);

            if (null == ids) {
                log.info("批量删除管理员不存在! ids = {}", ids);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            userService.deleteByIds(User.class, "id", ids);
            log.info("批量删除管理员成功! ids = {}", ids);

            return ResponseEntity.ok(null);
        } catch (Exception e) {
            log.error("批量删除管理员失败! ids = {}, e = {}", ids, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 禁用|启用
     * @param id
     * @return
     */
    @OperationLog(value = "禁用|启用管理员")
    @RequiresPermissions("user:status")
    @PutMapping(value = "/user/status/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable("id") Long id){
        try {
            log.debug("禁用|启用管理员参数! id = {}", id);
            User user = userService.findById(id);

            if (null == user) {
                log.info("管理员不存在! id = {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("管理员不存在!");
            }

            if(user.getIsAdmin()){
                log.info("禁用|启用管理员失败, 超级管理员不允许操作! id = {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("超级管理员不允许操作!");
            }

            //禁用启用
            user.setIsLock(!user.getIsLock());
            userService.updateSelective(user);

            log.info("禁用|启用管理员成功! id = {}", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error("禁用|启用管理员失败! id = {}, e = {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 跳转到管理员添加页面
     * @return
     */
    @OperationLog(value = "跳转到管理员添加页面")
    @RequiresPermissions("user:create")
    @GetMapping(value = "/user/add")
    public String add(ModelMap modelMap){
        List<Role> roleList = roleService.findAll();
        modelMap.put("roleList", roleList);
        log.info("跳转到管理员添加页面!");
        return BASE_PATH+"admin-add";
    }

    /**
     * 添加管理员
     * @param user
     * @param roleId  角色ID
     * @return
     */
    @OperationLog(value = "添加管理员")
    @RequiresPermissions("user:create")
    @ResponseBody
    @PostMapping(value = "/user")
    public ModelMap saveUser(User user, Long roleId){
        ModelMap messagesMap = new ModelMap();
        try {
            log.debug("添加管理员参数! user = {}", user);
            Boolean flag = userService.saveUserAndUserRole(user, roleId);
            if(flag){
                log.info("添加管理员成功! userId = {}", user.getId());
                messagesMap.put("status",SUCCESS);
                messagesMap.put("message","添加成功!");
                return messagesMap;
            }
            log.info("添加管理员失败, 但没有抛出异常! userId = {}", user.getId());
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","添加失败!");
            return messagesMap;
        } catch (Exception e) {
            log.error("添加管理失败! user = {}, e = {}", user, e);
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","添加失败!");
            return messagesMap;
        }
    }

    /**
     * 跳转到管理员修改页面
     * @return
     */
    @OperationLog(value = "跳转到管理员修改页面")
    @RequiresPermissions("user:update")
    @GetMapping(value = "/user/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap modelMap){
        User user = userService.findById(id);
        List<Role> roleList = roleService.findAll();
        Role role = roleService.findByUserId(user.getId());

        log.info("跳转到管理员修改页面！id = {}", id);
        modelMap.put("model", user);
        modelMap.put("roleList", roleList);
        modelMap.put("role", role);
        return BASE_PATH+"admin-edit";
    }

    /**
     * 更新管理员信息
     * @param id         用户ID
     * @param user       用户信息
     * @param oldRoleId  旧角色ID
     * @param roleId     角色ID
     * @return
     */
    @OperationLog(value = "更新管理员信息")
    @RequiresPermissions("user:update")
    @ResponseBody
    @PutMapping(value = "/user/{id}")
    public ModelMap updateUser(@PathVariable("id") Long id, User user, Long oldRoleId, Long roleId){
        ModelMap messagesMap = new ModelMap();
        try {
            log.debug("修改管理员参数! id= {}, user = {}", id, user);
            if(null == id){
                messagesMap.put("status",FAILURE);
                messagesMap.put("message","ID不能为空!");
                return messagesMap;
            }

            Boolean flag = userService.updateUserAndUserRole(user, oldRoleId, roleId);
            if(flag){
                log.info("修改管理员成功! id= {}, user = {}", id, user);
                messagesMap.put("status",SUCCESS);
                messagesMap.put("message","修改成功!");
                return messagesMap;
            }
            log.info("修改管理员失败,但没有抛出异常 ! id= {}, user = {}", id, user);
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","修改失败!");
            return messagesMap;
        } catch (Exception e) {
            log.error("修改管理失败! id = {}, user = {}, e = {}", id, user, e);
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","修改管理失败!");
            return messagesMap;
        }
    }

    /**
     * 检验用户名是否存在
     * @param username
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/user/isExist")
    public Boolean isExist(Long id, String username){
        boolean flag = true;
        try {
            log.debug("检验用户名是否存在参数! id= {}, username= {}", id, username);
            User record = userService.findByUserName(username);
            if (null != record && !record.getId().equals(id)) {
                flag = false;
            }
            log.info("检验用户名是否存在结果! flag = {}", flag);
            return flag;
        } catch (Exception e) {
            log.error("检验用户名是否存在失败! flag = {}, e = {}", flag, e);
        }
        return flag;
    }
}
