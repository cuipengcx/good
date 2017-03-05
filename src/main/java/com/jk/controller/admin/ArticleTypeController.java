package com.jk.controller.admin;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.controller.BaseController;
import com.jk.model.ArticleTypes;
import com.jk.service.ArticleTypeService;

/**
 * @author yangF
 *         Created by JK on 2017/2/7.
 */
@Controller
@RequestMapping(value="/admin/articletype", method = { RequestMethod.POST, RequestMethod.GET })
public class ArticleTypeController extends BaseController {

    private static final String BASE_PATH = "admin/article/";

    @Resource
    private ArticleTypeService articleTypeService;
    
    @Autowired
    HttpServletRequest request; //这里可以获取到request 
    
    @Autowired
    HttpServletResponse response;
    
    /**
     * 资讯类别列表
     *
     * @param pageNum   当前页码
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/list")
    public String list(ModelMap modelMap) {
        try {
            log.debug("查询资讯类别列表!");

            List<ArticleTypes> parentlist = articleTypeService.queryArticleTypes(-1l);
            
            List<ArticleTypes> allTypeslist = new ArrayList<ArticleTypes>();
            
            for(int i=0;i<parentlist.size();i++){
                ArticleTypes parenttype = parentlist.get(i);
                
                allTypeslist.add(parenttype);
                
                List<ArticleTypes> childlist = articleTypeService.queryArticleTypes(parenttype.getId());
                
                for(int j=0;j<childlist.size();j++){
                    ArticleTypes childtype = childlist.get(j);
                    
                    allTypeslist.add(childtype);
                }
            }
            
            log.info("分页查询资讯类别列表成功");
            
            modelMap.put("allTypeslist", allTypeslist);
            
        } catch (Exception e) {
            log.error("资讯列表错误! e = {}", e.getMessage());
        }
        return BASE_PATH + "articletypes-list";
    }
    
    /**
     * 资讯类别追加页面显示
     *
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/addArticleTypeInit")
    public String addArticleTypeInit(ModelMap modelMap,Long parentid, Long parentname) {
        try {
            log.debug("打开追加资讯类别页面! ");
            
            if(parentid == -1){
                modelMap.put("parentid", parentid);
                modelMap.put("parentname", "无父类");
            } else {
                ArticleTypes articletype = articleTypeService.findById(parentid);
                
                modelMap.put("parentid", parentid);
                modelMap.put("parentname", articletype.getName());
            }

        } catch (Exception e) {
            log.error("资讯类别列表错误! e = {}", e.getMessage());
        }
        return BASE_PATH + "articletypes-add";
    }
    
    
    /**
     * 资讯类别追加处理
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/doAddArticleType")
    public Map<String,String> doAddArticleType(ArticleTypes model) {

        Map<String,String> messagesMap = new HashMap<String,String>();

        try {
            log.debug("进行资讯类别追加! ");

            model.setStatus(1);
            model.setCreateTime(new Date());
            
            articleTypeService.save(model);
            
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","添加成功!");
            
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("资讯类别追加错误! e = {}", e.getMessage());
        }
        
        return messagesMap;
    }
    
    /**
     * 资讯删除处理
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/delArticleType")
    public Map<String,String> delArticleType(ArticleTypes model) {

        Map<String,String> messagesMap = new HashMap<String,String>();

        try {
            log.debug("进行资讯类别删除! id=" + model.getId());

            //如果删除的是父类，则删除所以下面的子类
            ArticleTypes articletype = articleTypeService.findById(model.getId());
            
            if(articletype.getParent_id() == -1){
                ArticleTypes deltype = new ArticleTypes();
                
                deltype.setParent_id(model.getId());
                
                articleTypeService.deleteByWhere(deltype);
            }
            
            //删除该类别
            articleTypeService.deleteById(model.getId());
            
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","删除成功!");
            
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("资讯类别删除错误! e = {}", e.getMessage());
        }
        
        return messagesMap;
    }
    
}
