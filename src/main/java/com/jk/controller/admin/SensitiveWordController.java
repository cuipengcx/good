package com.jk.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.jk.controller.BaseController;
import com.jk.model.SensitiveWord;
import com.jk.service.SensitiveWordService;

/**
 * @author yangF
 *         Created by JK on 2017/2/7.
 */
@Controller
@RequestMapping(value="/admin/sensitiveword", method = { RequestMethod.POST, RequestMethod.GET })
public class SensitiveWordController extends BaseController {

    private static final String BASE_PATH = "admin/manager/";

    @Resource
    private SensitiveWordService sensitivewordService;
    
    @Autowired
    HttpServletRequest request; //这里可以获取到request 
    
    @Autowired
    HttpServletResponse response;
    
    /**
     * 敏感词列表
     *
     * @param pageNum   当前页码
     * @param title  标题
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/list")
    public String list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            SensitiveWord model, String findtext, ModelMap modelMap) {
        try {
            log.debug("分页查询提现列表! pageNum = {}, username = {}, username = {} ", pageNum, findtext);

            PageInfo<SensitiveWord> pageInfo = sensitivewordService.findPage(pageNum, PAGESIZE, findtext);
            log.info("分页查询提现列表成功！ pageInfo = {}", pageInfo);
            modelMap.put("pageInfo", pageInfo);
            modelMap.put("findtext", findtext);
            modelMap.put("pageNum", pageNum);
            
        } catch (Exception e) {
            log.error("提现列表错误! e = {}", e.getMessage());
        }
        return BASE_PATH + "sensitiveword-list";
    }

    /**
     * 资讯追加页面显示
     *
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/addSwInit")
    public String addSwInit(ModelMap modelMap) {
        try {
            log.debug("打开追加资讯页面! ");

        } catch (Exception e) {
            log.error("资讯列表错误! e = {}", e.getMessage());
        }
        return BASE_PATH + "sensitiveword-add";
    }
    
    /**
     * 敏感词追加处理
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/doAddSw")
    public Map<String,String> doAddSw(SensitiveWord model) {

        Map<String,String> messagesMap = new HashMap<String,String>();

        try {
            log.debug("进行敏感词追加! ");
            
            model.setCreateTime(new Date());
            model.setVersion(0l);            
            
            sensitivewordService.save(model);
            
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","添加成功!");
            
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("敏感词追加错误! e = {}", e.getMessage());
        }
        
        return messagesMap;
    }
    
    
    /**
     * 敏感词编辑页面显示
     *
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/editSwInit")
    public String editSwInit(ModelMap modelMap,Long id) {
        try {
            log.debug("打开编辑敏感词页面! ");

            SensitiveWord sw = sensitivewordService.findById(id);
            modelMap.put("sw", sw);

        } catch (Exception e) {
            log.error("敏感词列表错误! e = {}", e.getMessage());
            e.printStackTrace();
        }
        return BASE_PATH + "sensitiveword-edit";
    }
    
    
    /**
     * 敏感词编辑处理
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/doEditSw")
    public Map<String,String> doEditSw(SensitiveWord model) {

        Map<String,String> messagesMap = new HashMap<String,String>();

        try {
            log.debug("进行敏感词编辑! id=" + model.getId());
            
            model.setModifyTime(new Date());
            model.setVersion(model.getVersion() + 1);
                        
            sensitivewordService.updateSelective(model);

            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","编辑成功!");
            
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("敏感词编辑错误! e = {}", e.getMessage());
        }
        
        return messagesMap;
    }
    
    /**
     * 敏感词删除处理
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/delSw")
    public Map<String,String> delSw(SensitiveWord model) {

        Map<String,String> messagesMap = new HashMap<String,String>();

        try {
            log.debug("进行敏感词删除! id=" + model.getId());

            sensitivewordService.deleteById(model.getId());

            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","删除成功!");
            
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("敏感词删除错误! e = {}", e.getMessage());
        }
        
        return messagesMap;
    }
    
}
