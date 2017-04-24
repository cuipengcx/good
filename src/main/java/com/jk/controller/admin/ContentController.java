package com.jk.controller.admin;

import com.github.pagehelper.PageInfo;
import com.jk.controller.BaseController;
import com.jk.model.Content;
import com.jk.model.ContentCat;
import com.jk.service.ContentCatService;
import com.jk.service.ContentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
                       ModelMap modelMap) {
        try {
            log.debug("分页查询内容列表参数! pageNum = {}", pageNum);

            ContentCat contentCat = contentCatService.findById(catId);

            if (null != catId) {
                Content content = new Content();
                content.setContentCatId(catId);
                PageInfo<Content> pageInfo = contentService.findPageListByWhere(pageNum, PAGESIZE, content);
                log.info("分页查询内容列表结果！ pageInfo = {}", pageInfo);

                modelMap.put("pageInfo", pageInfo);
            }

            modelMap.put("contentCat", contentCat);
        } catch (Exception e) {
            log.error("分页查询内容列表失败! e = {}", e);
        }
        return BASE_PATH + "content-list";
    }

    /**
     * 跳转到添加内容页面
     *
     * @param catId
     * @param modelMap
     * @return
     */
    @RequiresPermissions("content:create")
    @GetMapping("/add")
    public String add(Long catId, ModelMap modelMap) {
        ContentCat contentCat = contentCatService.findById(catId);
        modelMap.put("contentCat", contentCat);
        return BASE_PATH + "content-add";
    }

    /**
     * 保存内容
     * @param content
     * @return
     */
    @RequiresPermissions("content:create")
    @ResponseBody
    @PostMapping("/save")
    public ModelMap saveContent(Content content){
        ModelMap messagesMap = new ModelMap();
        try {
            contentService.save(content);
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","添加成功!");
            return messagesMap;
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","添加失败!");
            return messagesMap;
        }
    }

    /**
     * 跳转到编辑内容页面
     * @param id
     * @param modelMap
     * @return
     */
    @RequiresPermissions("content:update")
    @GetMapping("/edit")
    public String edit(Long id, ModelMap modelMap) {
        Content content = contentService.findById(id);

        if(null != content){
            ContentCat contentCat = contentCatService.findById(content.getContentCatId());
            modelMap.put("contentCat", contentCat);
        }

        modelMap.put("content", content);
        return BASE_PATH + "content-edit";
    }

    @RequiresPermissions("content:update")
    @ResponseBody
    @PostMapping("/update")
    public ModelMap updateContent(Content content){
        ModelMap messagesMap = new ModelMap();
        try {
            contentService.updateSelective(content);
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","更新成功!");
            return messagesMap;
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","更新失败!");
            return messagesMap;
        }
    }


    /**
     * 根据主键ID删除内容
     * @param id
     * @return
     */
    @RequiresPermissions("content:delete")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            log.debug("删除内容! id = {}", id);
            if (null == id) {
                log.info("删除内容不存在! id = {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("删除内容不存在!");
            }

            contentService.deleteById(id);
            log.info("删除内容成功! id = {}", id);
            return ResponseEntity.ok("删除成功!");
        } catch (Exception e) {
            log.error("删除内容失败! id = {}, e = {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
