package com.jk.controller.front;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jk.controller.BaseController;
import com.jk.model.Article;
import com.jk.service.ArticleService;

/**
 * 首页
 * Created by JK on 2017/1/22.
 */
@Controller
@RequestMapping("/front")
public class FrontIndexController extends BaseController{

    private static final String BASE_PATH = "front/";

    @Resource
    private ArticleService articleservice;
    
    @Autowired
    HttpServletRequest request; //这里可以获取到request 
    
    @Autowired
    HttpServletResponse response;
    
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = {"","/index"})
    public String index(ModelMap modelMap){
        log.info("------进入首页-------");
        
        try{
            log.info("取到首页轮播图");
            Article articlewhere = new Article();
            
            //83 首页轮播图
            articlewhere.setType_id(83);
            
            List<Article> bannerlist = articleservice.findListByWhere(articlewhere);
            
            modelMap.put("bannerlist", bannerlist);
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return BASE_PATH+"index";
    }

    /**
     * 未授权页面
     * @return
     */
    @RequestMapping(value = "/403")
    public String unauthorized(){
        log.info("------没有权限-------");
        return BASE_PATH+"403";
    }
}
