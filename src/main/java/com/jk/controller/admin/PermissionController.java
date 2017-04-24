package com.jk.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jk.controller.BaseController;
import com.jk.model.Permission;
import com.jk.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
     * 分页查询权限列表
     * @param pageNum 当前页码
     * @param modelMap
     * @return
     */
    @RequiresPermissions("permission:list")
    @GetMapping
    public String list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       Permission model,
                       ModelMap modelMap){
        try {
            log.debug("分页查询权限列表参数! pageNum = {}, name = {}", pageNum, model.getName());
            PageInfo<Permission> pageInfo = permissionService.findPage(pageNum, PAGESIZE, model.getName());
            log.info("分页查询权限列表结果！ pageInfo = {}", pageInfo);
            modelMap.put("pageInfo", pageInfo);
            modelMap.put("model", model);
        } catch (Exception e) {
            log.error("分页查询权限列表失败! pageNum = {}, name = {}, e = {}", pageNum, model.getName(), e);
        }
        return BASE_PATH+"admin-permission";
    }

    /**
     * 根据主键ID删除权限
     * @param id
     * @return
     */
    @CacheEvict(value = "menuListCache", allEntries = true)
    @RequiresPermissions("permission:delete")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
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
        } catch (Exception e) {
            log.error("删除权限失败! id = {}, e = {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 跳转到权限添加页面
     * @return
     */
    @RequiresPermissions("permission:create")
    @GetMapping("/add")
    public String add(){
        log.info("跳转到权限添加页面!");
        return BASE_PATH+"admin-permission-add";
    }

    /**
     * 添加权限
     * @param permission
     * @return
     */
    @CacheEvict(value = "menuListCache", allEntries = true)
    @RequiresPermissions("permission:create")
    @ResponseBody
    @PostMapping
    public ModelMap savePermission(Permission permission){
        ModelMap messagesMap = new ModelMap();
        try {
            log.debug("添加权限参数! permission = {}", permission);

            permission.setIsLock(false);
            permission.setParentId(permission.getParentId() == null ? 0 : permission.getParentId());
            permissionService.save(permission);
            log.info("添加权限成功! permissionId = {}", permission.getId());
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","添加成功!");
            return messagesMap;
        } catch (Exception e) {
            log.error("添加权限失败! permission = {}, e = {}", permission, e);
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","添加失败!");
            return messagesMap;
        }
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
        try {
            log.debug("根据资源类型获取权限列表，类型type = {}", type);
            List<Permission> permissionList = permissionService.findListByType(type);
            log.info("根据资源类型获取权限成功! permissionList = {}", permissionList);

            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","获取成功!");
            messagesMap.put("permissionList", permissionList);
            return messagesMap;
        } catch (Exception e) {
            //e.printStackTrace();
            log.error("根据资源类型获取权限列表失败! type = {}, e = {}", type, e);
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","获取失败!");
            return messagesMap;
        }
    }

    /**
     * 跳转到权限编辑页面
     * @return
     */
    @RequiresPermissions("permission:update")
    @GetMapping(value = "/edit/{id}/{type}")
    public String edit(@PathVariable("id") Long id, @PathVariable("type") String type, ModelMap modelMap){
        log.info("跳转到权限编辑页面！id = {}, type = {}", id, type);
        Permission permission = permissionService.findById(id);
        String parentType = "";
        if("1".equals(type)){
            parentType = "0";
        }else if("2".equals(type)){
            parentType = "1";
        }
        //父节点列表
        List<Permission> permissionList = permissionService.findListByType(parentType);

        modelMap.put("model", permission);
        modelMap.put("permissionList", permissionList);
        return BASE_PATH+"admin-permission-edit";
    }

    /**
     * 更新权限信息
     * @param id
     * @param permission
     * @return
     */
    @CacheEvict(value = "menuListCache", allEntries = true)
    @RequiresPermissions("permission:update")
    @ResponseBody
    @PutMapping(value = "/{id}")
    public ModelMap updateRole(@PathVariable("id") Long id, Permission permission){
        ModelMap messagesMap = new ModelMap();
        try {
            log.debug("修改权限参数! id= {}, permission = {}", id, permission);

            if(null == id){
                messagesMap.put("status",FAILURE);
                messagesMap.put("message","ID不能为空!");
                return messagesMap;
            }

            permissionService.updateSelective(permission);
            log.info("修改权限成功! id= {}, permission = {}", id, permission);
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","修改成功!");
            return messagesMap;
        } catch (Exception e) {
            log.error("修改权限失败! id = {}, permission = {}, e = {}", id, permission, e);
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","修改失败!");
            return messagesMap;
        }
    }

}
