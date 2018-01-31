package com.jk.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.jk.common.base.controller.BaseController;
import com.jk.modules.sys.model.Dept;
import com.jk.modules.sys.service.DeptService;
import com.jk.modules.sys.vo.TreeNode;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


/**
 * @package: com.jk.modules.sys.controller
 * @description: 部门管理控制层
 * @author: cuiP
 * @date: 2018/1/30 15:01
 * @version: V1.0.0
 */
@Controller
@RequestMapping("/admin/dept")
public class DeptController extends BaseController{

    private static final String BASE_PATH = "admin/manager/";

    @Resource
    private DeptService deptService;

    /**
     * 分页查询部门列表
     * @param pageNum 当前页码
     * @param deptId 部门ID
     * @param name 部门名称
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param modelMap
     * @return
     */
    @RequiresPermissions("dept:list")
    @GetMapping
    public String list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       Long deptId,
                       String name,
                       String startTime,
                       String endTime,
                       ModelMap modelMap){
        log.debug("分页查询部门列表参数! pageNum = {}, organizationId = {}, name = {}, startTime = {},endTime = {}",
                pageNum, deptId, name, startTime, endTime);

        PageInfo<Dept> pageInfo = deptService.findPage(pageNum, PAGESIZE, deptId, name, startTime, endTime);
        log.info("分页查询部门列表结果！pageInfo = {}", pageInfo);

        modelMap.put("pageInfo", pageInfo);
        modelMap.put("deptId", deptId);
        modelMap.put("name", name);
        modelMap.put("startTime", startTime);
        modelMap.put("endTime", endTime);
        return BASE_PATH + "admin-dept-list";
    }


    /**
     * 查询部门树
     * @return
     */
    @GetMapping("/tree")
    public ResponseEntity<List<TreeNode>> treeList() {
        List<TreeNode> treeNodeList = deptService.findTreeList();
        return ResponseEntity.ok(treeNodeList);
    }
}
