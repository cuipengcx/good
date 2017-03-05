package com.jk.controller.front;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.jk.controller.BaseController;
import com.jk.model.Article;
import com.jk.model.ArticleTypes;
import com.jk.service.ArticleService;

/**
 * 首页
 * Created by JK on 2017/1/22.
 */
@Controller
@RequestMapping("/front/find")
public class FrontFindController extends BaseController{

    private static final String BASE_PATH = "front/find/";

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
    @RequestMapping(value = {"/list"})
    public String list(ModelMap modelMap ){
        log.info("------发现首页-------");
        
        try{
            log.info("取到故事，新闻，活动及最新文章列表!");
            
            Integer[] pageNum = new Integer[4];
            
            for(int i=0;i<pageNum.length;i++){
                pageNum[i] = 1;
            }
            
            //最新文章(所有)
            Integer[] typeid0 = new Integer[3];
            typeid0[0] = 118;
            typeid0[1] = 119;
            typeid0[2] = 120;
            
            PageInfo<Article> pageInfo0 = articleservice.findPageFront(pageNum[0], PAGESIZE, typeid0, "0");
            
            modelMap.put("pageInfo0", pageInfo0);
            
            //118 故事
            Integer[] typeid1 = new Integer[1];
            typeid1[0] = 118;
            
            PageInfo<Article> pageInfo1 = articleservice.findPageFront(pageNum[1], PAGESIZE, typeid1, "1");
            
            modelMap.put("pageInfo1", pageInfo1);
            
            //119 新闻
            Integer[] typeid2 = new Integer[1];
            typeid2[0] = 119;
            
            PageInfo<Article> pageInfo2 = articleservice.findPageFront(pageNum[2], PAGESIZE, typeid2, "1");
            
            modelMap.put("pageInfo2", pageInfo2);
            
            //120 活动
            Integer[] typeid3 = new Integer[1];
            typeid3[0] = 120;
            
            PageInfo<Article> pageInfo3 = articleservice.findPageFront(pageNum[3], PAGESIZE, typeid3, "1");
            
            modelMap.put("pageInfo3", pageInfo3);

            modelMap.put("pageNum", pageNum);
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return BASE_PATH+"list";
    }

    /**
     * 子类别检索
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/list_page"})
    public Map<String,Object> list_page(Integer pageNum,Integer pagetype){
        log.info("------发现首页-------");
        
        Map<String,Object> messagesMap = new HashMap<String,Object>();
        
        try{
            log.info("取到故事，新闻，活动及最新文章列表!");
            
            if(pagetype == null){
                pagetype = 0;
            }

            if(pageNum == null){
                pageNum = 1;
            }
            
            PageInfo<Article> pageInfo = null;
            
            if(pagetype == 0){
                //最新文章(所有)
                Integer[] typeid0 = new Integer[3];
                typeid0[0] = 118;
                typeid0[1] = 119;
                typeid0[2] = 120;
                
                pageInfo = articleservice.findPageFront(pageNum, PAGESIZE, typeid0, "0");
                
            } else if(pagetype == 1){
                
                //118 故事
                Integer[] typeid1 = new Integer[1];
                typeid1[0] = 118;
                
                pageInfo = articleservice.findPageFront(pageNum, PAGESIZE, typeid1, "1");
                
            } else if(pagetype == 2){
                
                //119 新闻
                Integer[] typeid2 = new Integer[1];
                typeid2[0] = 119;
                
                pageInfo = articleservice.findPageFront(pageNum, PAGESIZE, typeid2, "1");

            } else if(pagetype == 3){
                
                //120 活动
                Integer[] typeid3 = new Integer[1];
                typeid3[0] = 120;
                
                pageInfo = articleservice.findPageFront(pageNum, PAGESIZE, typeid3, "1");

            }
            
            messagesMap.put("pageInfo",pageInfo);
            
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","编辑成功!");

            
        } catch (Exception e){
            messagesMap.put("status",FAILURE);
            log.error("资讯编辑错误! e = {}", e.getMessage());
            
            e.printStackTrace();
        }
        
        return messagesMap;
    }
    
}
