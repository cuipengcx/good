package com.good.modules.cms.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.good.common.annotation.OperationLog;
import com.good.common.base.controller.BaseController;
import com.good.common.security.token.FormToken;
import com.good.modules.cms.model.Content;
import com.good.modules.cms.model.ContentCat;
import com.good.modules.cms.service.ContentCatService;
import com.good.modules.cms.service.ContentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 内容管理
 *
 * @author cuiP
 *         Created by JK on 2017/4/20.
 */
@Controller
@RequestMapping("/admin/content")
public class ContentController extends BaseController {

    private static final String BASE_PATH = "admin/content/";

    @Resource
    private ContentService contentService;
    @Resource
    private ContentCatService contentCatService;
    @Value("${imageUrl}")
    public String IMAGE_URL;

    /**
     * 分页查询内容列表
     *
     * @param pageNum  当前页码
     * @param modelMap
     * @return
     */
    @RequiresPermissions("content:list")
    @GetMapping
    public String list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       Long catId,
                       String title,
                       String startTime,
                       String endTime,
                       ModelMap modelMap) {
        log.debug("分页查询内容列表参数! pageNum = {}", pageNum);

        ContentCat contentCat = contentCatService.selectById(catId);

        if (null != catId) {
            Page<Content> pageInfo = contentService.findPage(pageNum, PAGESIZE, catId, title, startTime, endTime);
            log.info("分页查询内容列表结果！ pageInfo = {}", pageInfo);

            modelMap.put("pageInfo", pageInfo);
        }

        modelMap.put("title", title);
        modelMap.put("startTime", startTime);
        modelMap.put("endTime", endTime);
        modelMap.put("imageUrl", IMAGE_URL);
        modelMap.put("contentCat", contentCat);
        return BASE_PATH + "content-list";
    }

    /**
     * 跳转到添加内容页面
     *
     * @param catId
     * @param modelMap
     * @return
     */
    @FormToken(save = true)
    @RequiresPermissions("content:create")
    @GetMapping("/add")
    public String add(Long catId, ModelMap modelMap) {
        ContentCat contentCat = contentCatService.selectById(catId);
        modelMap.put("contentCat", contentCat);
        return BASE_PATH + "content-add";
    }

    /**
     * 保存内容
     * @param content
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "添加内容")
    @RequiresPermissions("content:create")
    @ResponseBody
    @PostMapping("/save")
    public ModelMap saveContent(Content content){
        ModelMap messagesMap = new ModelMap();
        contentService.insert(content);
        messagesMap.put("status",SUCCESS);
        messagesMap.put("message","添加成功!");
        return messagesMap;
    }

    /**
     * 跳转到编辑内容页面
     * @param id
     * @param modelMap
     * @return
     */
    @FormToken(save = true)
    @RequiresPermissions("content:update")
    @GetMapping("/edit")
    public String edit(Long id, ModelMap modelMap) {
        Content content = contentService.selectById(id);

        if(null != content){
            ContentCat contentCat = contentCatService.selectById(content.getContentCatId());
            modelMap.put("contentCat", contentCat);
        }

        modelMap.put("content", content);
        modelMap.put("imageUrl", IMAGE_URL);
        return BASE_PATH + "content-edit";
    }

    /**
     * 更新内容
     * @param content
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "编辑内容")
    @RequiresPermissions("content:update")
    @ResponseBody
    @PostMapping("/update")
    public ModelMap updateContent(Content content){
        ModelMap messagesMap = new ModelMap();
        contentService.updateById(content);
        messagesMap.put("status",SUCCESS);
        messagesMap.put("message","编辑成功!");
        return messagesMap;
    }


    /**
     * 根据主键ID删除内容
     * @param id
     * @return
     */
    @OperationLog(value = "删除内容")
    @RequiresPermissions("content:delete")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        log.debug("删除内容! id = {}", id);
        if (null == id) {
            log.info("删除内容不存在! id = {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("删除内容不存在!");
        }

        contentService.deleteById(id);
        log.info("删除内容成功! id = {}", id);
        return ResponseEntity.ok("删除成功!");
    }
}
