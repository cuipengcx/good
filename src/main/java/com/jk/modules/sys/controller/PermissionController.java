package com.jk.modules.sys.controller;

import com.jk.common.annotation.OperationLog;
import com.jk.common.base.controller.BaseController;
import com.jk.common.security.token.FormToken;
import com.jk.common.security.xss.XssHttpServletRequestWrapper;
import com.jk.modules.sys.model.Permission;
import com.jk.modules.sys.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 权限Controller
 * Created by cuiP on 2017/2/8.
 */
@Controller
@RequestMapping("/admin/permission")
public class PermissionController extends BaseController{

    private static final String BASE_PATH = "admin/manager/";

    @Resource
    private PermissionService permissionService;

    /**
     * 跳转到权限列表页面
     * @return
     */
    @RequiresPermissions("permission:list")
    @GetMapping
    public String toList(){
        return BASE_PATH + "admin-permission";
    }

    /**
     * 查询权限列表
     *
     * @return
     */
    @RequiresPermissions("permission:list")
    @ResponseBody
    @GetMapping("/list")
    public List<Permission> list(String menuName){
        return permissionService.findListByMenuName(menuName);
    }

    /**
     * 根据主键ID删除权限
     * @param id
     * @return
     */
    @OperationLog(value = "删除权限")
    @CacheEvict(value = "menuListCache", allEntries = true)
    @RequiresPermissions("permission:delete")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        log.debug("删除权限! id = {}", id);
        if (null == id) {
            log.info("删除权限不存在! id = {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("删除权限不存在!");
        }

        Boolean flag = permissionService.deletePermissionAndRolePermissionByPermissionId(id);
        if(flag){
            log.info("删除权限成功! id = {}", id);
            return ResponseEntity.ok("删除成功！");
        }

        log.info("删除权限失败，但没有抛出异常! id = {}", id);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 跳转到权限添加页面
     * @return
     */
    @FormToken(save = true)
    @RequiresPermissions("permission:create")
    @GetMapping("/add")
    public String add(){
        log.info("跳转到权限添加页面!");
        return BASE_PATH + "admin-permission-add";
    }

    /**
     * 添加权限
     * @param permission
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "添加权限")
    @CacheEvict(value = "menuListCache", allEntries = true)
    @RequiresPermissions("permission:create")
    @ResponseBody
    @PostMapping
    public ModelMap savePermission(HttpServletRequest request, Permission permission){
        ModelMap messagesMap = new ModelMap();
        log.debug("添加权限参数! permission = {}", permission);

        //获取不进行xss过滤的request对象
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);

        permission.setIcon(orgRequest.getParameter("icon"));
        permission.setIsLock(false);
        permission.setParentId(permission.getParentId() == null ? 0 : permission.getParentId());
        permissionService.save(permission);
        log.info("添加权限成功! permissionId = {}", permission.getId());
        messagesMap.put("status",SUCCESS);
        messagesMap.put("message","添加成功!");
        return messagesMap;
    }

    /**
     * 根据资源类型获取权限列表
     * @param type
     * @return
     */
    @ResponseBody
    @GetMapping("/getPermissionList")
    public ModelMap getPermissionList(String type){
        ModelMap messagesMap = new ModelMap();
        log.debug("根据资源类型获取权限列表，类型type = {}", type);
        List<Permission> permissionList = permissionService.findListByType(type);
        log.info("根据资源类型获取权限成功! permissionList = {}", permissionList);

        messagesMap.put("status",SUCCESS);
        messagesMap.put("message","获取成功!");
        messagesMap.put("permissionList", permissionList);
        return messagesMap;
    }

    /**
     * 跳转到权限编辑页面
     * @return
     */
    @FormToken(save = true)
    @RequiresPermissions("permission:update")
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap modelMap){
        log.info("跳转到权限编辑页面！id = {}", id);
        Permission permission = permissionService.findById(id);

        String parentType = "";
        if("1".equals(permission.getType())){
            parentType = "0";
        }else if("2".equals(permission.getType())){
            parentType = "1";
        }

        //父节点列表
        List<Permission> permissionList = permissionService.findListByType(parentType);

        modelMap.put("model", permission);
        modelMap.put("permissionList", permissionList);
        return BASE_PATH + "admin-permission-edit";
    }

    /**
     * 更新权限信息
     * @param id
     * @param permission
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "编辑权限")
    @CacheEvict(value = "menuListCache", allEntries = true)
    @RequiresPermissions("permission:update")
    @ResponseBody
    @PutMapping(value = "/{id}")
    public ModelMap updatePermission(HttpServletRequest request, @PathVariable("id") Long id, Permission permission){
        ModelMap messagesMap = new ModelMap();
        log.debug("编辑权限参数! id= {}, permission = {}", id, permission);

        if(null == id){
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","ID不能为空!");
            return messagesMap;
        }

        //获取不进行xss过滤的request对象
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);

        permission.setIcon(orgRequest.getParameter("icon"));

        permissionService.updateSelective(permission);
        log.info("编辑权限成功! id= {}, permission = {}", id, permission);
        messagesMap.put("status",SUCCESS);
        messagesMap.put("message","编辑成功!");
        return messagesMap;
    }

}
