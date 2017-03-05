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
import com.jk.model.Recommend;
import com.jk.service.RecommendService;

/**
 * @author yangF
 *         Created by JK on 2017/2/7.
 */
@Controller
@RequestMapping(value="/admin/recommend", method = { RequestMethod.POST, RequestMethod.GET })
public class RecommendController extends BaseController {

    private static final String BASE_PATH = "admin/recommend/";

    @Resource
    private RecommendService recommendService;
    
    @Autowired
    HttpServletRequest request; //这里可以获取到request 
    
    @Autowired
    HttpServletResponse response;
    
    /**
     * 推荐列表
     *
     * @param pageNum   当前页码
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/list")
    public String list(ModelMap modelMap, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, String findtype) {
        try {
            log.debug("查询推荐列表!");

            PageInfo<Recommend> pageInfo = recommendService.queryAllRecommend(pageNum, PAGESIZE, findtype);
            
            log.info("查询推荐列表成功");
            
            modelMap.put("pageInfo", pageInfo);
            
        } catch (Exception e) {
            log.error("查询推荐错误! e = {}", e.getMessage());
        }
        return BASE_PATH + "recommend-list";
    }
    
    /**
     * 推荐追加页面显示
     *
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/addRecommendInit")
    public String addRecommendInit(ModelMap modelMap) {
        try {
            log.debug("打开追加推荐页面! ");
            
        } catch (Exception e) {
            log.error("推荐追加错误! e = {}", e.getMessage());
        }
        return BASE_PATH + "recommend-add";
    }
    
    /**
     * 推荐追加处理
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/doAddRecommend")
    public Map<String,String> doAddRecommend(Recommend model) {

        Map<String,String> messagesMap = new HashMap<String,String>();

        try {
            log.debug("进行推荐追加! ");

            //判断该ID是否存在可用信息
            
            if(recommendService.checkRecommendId(model.getType(),model.getRecommend_id())){
                model.setCreateTime(new Date());
                model.setIs_use(1);
                
                recommendService.save(model);
                
                messagesMap.put("status",SUCCESS);
                messagesMap.put("message","添加成功!");
            } else {
                messagesMap.put("status",FAILURE);
                messagesMap.put("message","无此内容或者已被禁用或者不可用!");
            }

        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("推荐追加错误! e = {}", e.getMessage());
        }
        
        return messagesMap;
    }
    
    
    /**
     * 推荐编辑页面显示
     *
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/editArticleInit")
    public String editArticleInit(ModelMap modelMap,Long id) {
        try {
            log.debug("打开编辑推荐页面! ");
            
            //findrecommend
            Recommend recommend = recommendService.findById(id);
            modelMap.put("recommend", recommend);
            
        } catch (Exception e) {
            log.error("推荐编辑错误! e = {}", e.getMessage());
            e.printStackTrace();
        }
        return BASE_PATH + "recommend-edit";
    }
    
    
    /**
     * 推荐编辑处理
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/doEditRecommend")
    public Map<String,String> doEditArticle(Recommend model) {

        Map<String,String> messagesMap = new HashMap<String,String>();

        try {
            log.debug("进行推荐编辑! id=" + model.getId());
            
            //判断该ID是否存在可用信息
            
            if(recommendService.checkRecommendId(model.getType(),model.getRecommend_id())){
                model.setModifyTime(new Date());
                
                recommendService.updateSelective(model);
                
                messagesMap.put("status",SUCCESS);
                messagesMap.put("message","编辑成功!");
            } else {
                messagesMap.put("status",FAILURE);
                messagesMap.put("message","无此内容或者已被禁用或者不可用!");
            }
            
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("推荐编辑错误! e = {}", e.getMessage());
        }
        
        return messagesMap;
    }
    
    /**
     * 推荐删除处理
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/delRecommend")
    public Map<String,String> delArticleType(Recommend model) {

        Map<String,String> messagesMap = new HashMap<String,String>();

        try {
            log.debug("进行推荐删除! id=" + model.getId());

            //删除该类别
            recommendService.deleteById(model.getId());
            
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","删除成功!");
            
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("推荐删除错误! e = {}", e.getMessage());
        }
        
        return messagesMap;
    }
    
    /**
     * 上架下架处理
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/updateIsuse")
    public Map<String,String> updateIsuse(Recommend model) {

        Map<String,String> messagesMap = new HashMap<String,String>();

        try {
            log.debug("更新推荐位上架下架信息! id=" + model.getId());

            recommendService.updateSelective(model);

            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","更新上架下架成功!");
            
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("更新资讯上架下架错误! e = {}", e.getMessage());
        }
        
        return messagesMap;
    }
}
