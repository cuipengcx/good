package com.jk.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jk.annotation.OperationLog;
import com.jk.controller.BaseController;
import com.jk.model.Role;
import com.jk.service.PermissionService;
import com.jk.service.RolePermissionService;
import com.jk.service.RoleService;
import com.jk.vo.TreeNode;
import com.xiaoleilu.hutool.json.JSONUtil;
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
 * 角色controller
 * Created by cuiP on 2017/1/19.
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController{

    private static final String BASE_PATH = "admin/manager/";

    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RolePermissionService rolePermissionService;

    /**
     * 分页查询角色列表
     * @param pageNum 当前页码
     * @param modelMap
     * @return
     */
    @OperationLog(value = "分页查询角色列表")
    @RequiresPermissions("role:list")
    @GetMapping
    public String list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       ModelMap modelMap){
        try {
            log.debug("分页查询角色列表参数! pageNum = {}", pageNum);
            PageInfo<Role> pageInfo = roleService.findPageListByWhere(pageNum, PAGESIZE, null);
            log.info("分页查询角色列表结果！ pageInfo = {}", pageInfo);
            modelMap.put("pageInfo", pageInfo);
        } catch (Exception e) {
            log.error("分页查询角色列表失败! e = {}", e);
        }
        return BASE_PATH+"admin-role";
    }

    /**
     * 根据主键ID删除角色
     * @param id
     * @return
     */
    @OperationLog(value = "根据主键ID删除角色")
    @RequiresPermissions("role:delete")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            log.debug("删除管理员! id = {}", id);
            if (null == id) {
                log.info("删除角色不存在! id = {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("删除角色不存在!");
            }
//            roleService.deleteById(id);
            Boolean flag =  roleService.deleteRoleAndRolePermissionByRoleId(id);
            if(flag){
                log.info("删除角色成功! id = {}", id);
                return ResponseEntity.ok("删除成功!");
            }
            log.info("删除角色失败，但没有抛出异常! id = {}", id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            log.error("删除角色失败! id = {}, e = {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 跳转到角色添加页面
     * @return
     */
    @OperationLog(value = "跳转到角色添加页面")
    @RequiresPermissions("role:create")
    @GetMapping(value = "/add")
    public String add(ModelMap modelMap){
        List<TreeNode> treeNodeList = permissionService.findTreeList();
        String jsonStr = JSONUtil.toJsonStr(treeNodeList);
        modelMap.put("treeNodeList", jsonStr);
        return BASE_PATH+"admin-role-add";
    }

    /**
     * 添加角色并分配权限
     * @param role
     * @return
     */
    @OperationLog(value = "添加角色并分配权限")
    @RequiresPermissions("role:create")
    @ResponseBody
    @PostMapping
    public ModelMap saveRole(Role role,Long[] permissionIds){
        ModelMap messagesMap = new ModelMap();
        try {
            log.debug("添加角色并分配权限参数! role = {}, permissionIds = {}", role, permissionIds);

            Boolean flag = roleService.saveRoleAndRolePermission(role, permissionIds);
            if(flag){
                log.info("添加角色并分配权限成功! roleId = {}", role.getId());
                messagesMap.put("status",SUCCESS);
                messagesMap.put("message","添加成功!");
                return messagesMap;
            }

            log.info("添加角色并分配权限失败，但没有抛出异常! roleId = {}", role.getId());
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","添加失败!");
            return messagesMap;
        } catch (Exception e) {
            log.error("添加角色并分配权限失败! role = {}, e = {}", role, e);
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","添加角色并分配权限失败!");
            return messagesMap;
        }
    }

    /**
     * 跳转到角色编辑页面
     * @return
     */
    @OperationLog(value = "跳转到角色编辑页面")
    @RequiresPermissions("role:update")
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap modelMap){
        try {
            log.debug("跳转到编辑角色信息页面参数! id = {}", id);
            Role role = roleService.findById(id);
            //所有权限列表
            List<TreeNode> treeNodeList = permissionService.findTreeList();
            String jsonStr = JSONUtil.toJsonStr(treeNodeList);
            //当前角色所拥有的权限ID集合
            String permissionIds = rolePermissionService.findPermissionIdsByRoleId(role.getId());
            log.info("跳转到编辑角色信息页面成功!, id = {}", id);
            modelMap.put("model", role);
            modelMap.put("treeNodeList", jsonStr);
            modelMap.put("permissionIds", permissionIds);
            return BASE_PATH+"admin-role-edit";
        } catch (Exception e) {
//            e.printStackTrace();
            log.error("跳转到编辑角色信息页面失败! id = {} e = {}", id, e);
        }
        return "";
    }

    /**
     * 更新角色信息
     * @param id
     * @param role
     * @return
     */
    @OperationLog(value = "更新角色信息")
    @CacheEvict(value = "menuListCache", allEntries = true)
    @RequiresPermissions("role:update")
    @ResponseBody
    @PutMapping(value = "/{id}")
    public ModelMap updateRole(@PathVariable("id") Long id, Long[] permissionIds, Role role){
        ModelMap messagesMap = new ModelMap();
        try {
            log.debug("修改角色参数! id= {}, permissionIds= {}, role = {}", id, permissionIds, role);

            if(null == id){
                messagesMap.put("status",FAILURE);
                messagesMap.put("message","ID不能为空!");
                return messagesMap;
            }

            boolean flag = roleService.updateRoleAndRolePermission(role, permissionIds);
            if(flag){
                log.info("修改角色成功! id= {}, permissionIds = {}, role = {}", id, permissionIds, role);
                messagesMap.put("status",SUCCESS);
                messagesMap.put("message","修改成功!");
                return messagesMap;
            }
            log.info("修改角色失败，但没有抛出异常! id= {}, permissionIds = {}, role = {}", id, permissionIds, role);
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","修改失败!");
            return messagesMap;
        } catch (Exception e) {
            log.error("修改角色失败! id = {}, permissionIds= {}, role = {}, e = {}", id, permissionIds, role, e);
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","修改角色失败!");
            return messagesMap;
        }
    }

    /**
     * 检验角色名是否存在
     * @param id
     * @param name
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/isExist")
    public Boolean isExist(Long id, String name){
        boolean flag = true;
        try {
            log.debug("检验角色名是否存在参数! id= {}, name= {}", id, name);
            Role record = roleService.findByName(name);
            if (null != record && !record.getId().equals(id)) {
                flag = false;
            }
            log.info("检验角色名是否存在结果! flag = {}", flag);
            return flag;
        } catch (Exception e) {
            log.error("检验角色名是否存在失败! flag = {}, e = {}", flag, e);
        }
        return flag;
    }


}
