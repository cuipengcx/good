package com.jk.controller.admin;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.jk.controller.BaseController;
import com.jk.model.Article;
import com.jk.model.ArticleTypes;
import com.jk.service.ArticleService;
import com.jk.service.ArticleTypeService;

/**
 * @author yangF
 *         Created by JK on 2017/2/7.
 */
@Controller
@RequestMapping(value="/admin/article", method = { RequestMethod.POST, RequestMethod.GET })
public class ArticleController extends BaseController {

    private static final String BASE_PATH = "admin/article/";

    @Resource
    private ArticleService articleService;
    
    @Resource
    private ArticleTypeService articleTypeService;
    
    @Autowired
    HttpServletRequest request; //这里可以获取到request 
    
    @Autowired
    HttpServletResponse response;
    
    /**
     * 资讯列表
     *
     * @param pageNum   当前页码
     * @param title  标题
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/list")
    public String list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            Article model, String findtext, String startTime, String endTime, ModelMap modelMap, String ordertype ,String ordervalue) {
        try {
            log.debug("分页查询资讯列表! pageNum = {}, username = {}, username = {} ", pageNum, findtext, startTime, endTime);
            
            if(ordertype == null){
                ordertype = "0";
                ordervalue = "0";
            }
            
            PageInfo<Article> pageInfo = articleService.findPage(pageNum, PAGESIZE, findtext,ordertype, ordervalue);
            log.info("分页查询资讯列表成功！ pageInfo = {}", pageInfo);
            modelMap.put("pageInfo", pageInfo);
            modelMap.put("text", findtext);
            modelMap.put("ordertype", ordertype);
            modelMap.put("ordervalue", ordervalue);
            modelMap.put("findtext", findtext);
            modelMap.put("pageNum", pageNum);
            
        } catch (Exception e) {
            log.error("资讯列表错误! e = {}", e.getMessage());
        }
        return BASE_PATH + "article-list";
    }
    
    /**
     * 资讯追加页面显示
     *
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/addArticleInit")
    public String addArticleInit(ModelMap modelMap) {
        try {
            log.debug("打开追加资讯页面! ");

            List<ArticleTypes> typeslist = articleTypeService.queryArticleTypes(-1l);
            
            modelMap.put("typeslist", typeslist);
            
        } catch (Exception e) {
            log.error("资讯列表错误! e = {}", e.getMessage());
        }
        return BASE_PATH + "article-add";
    }
    
    
    /**
     * 资讯追加处理
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/doAddArticle")
    public Map<String,String> doAddArticle(Article model) {

        Map<String,String> messagesMap = new HashMap<String,String>();

        try {
            log.debug("进行资讯追加! ");
            
            model.setLocation_pc(0);
            model.setIs_use(1);
            model.setTime(new Date());
            
            articleService.save(model);
            
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","添加成功!");
            
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("资讯追加错误! e = {}", e.getMessage());
        }
        
        return messagesMap;
    }
    
    
    /**
     * 资讯编辑页面显示
     *
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/editArticleInit")
    public String editArticleInit(ModelMap modelMap,Long id) {
        try {
            log.debug("打开编辑资讯页面! ");
            
            //findarticle
            Article article = articleService.findById(id);
            modelMap.put("article", article);
            
            //articletype
            ArticleTypes artcletype = articleTypeService.findById(new Long(article.getType_id()));
            
            modelMap.put("artcletype", artcletype);
            
            //parenttypelist
            List<ArticleTypes> typeslist = articleTypeService.queryArticleTypes(-1l);
            
            modelMap.put("typeslist", typeslist);

            //childtypelist
            List<ArticleTypes> childtypeslist = articleTypeService.queryChildTypes(new Long(article.getType_id()));

            modelMap.put("childtypeslist", childtypeslist);

        } catch (Exception e) {
            log.error("资讯列表错误! e = {}", e.getMessage());
            e.printStackTrace();
        }
        return BASE_PATH + "article-edit";
    }
    
    
    /**
     * 资讯编辑处理
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/doEditArticle")
    public Map<String,String> doEditArticle(Article model) {

        Map<String,String> messagesMap = new HashMap<String,String>();

        try {
            log.debug("进行资讯编辑! id=" + model.getId());
            
            model.setModifyTime(new Date());
            
            articleService.updateSelective(model);

            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","编辑成功!");
            
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("资讯编辑错误! e = {}", e.getMessage());
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
    @GetMapping(value = "/delArticle")
    public Map<String,String> delArticle(Article model) {

        Map<String,String> messagesMap = new HashMap<String,String>();

        try {
            log.debug("进行资讯删除! id=" + model.getId());

            articleService.deleteById(model.getId());

            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","删除成功!");
            
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("资讯删除错误! e = {}", e.getMessage());
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
    public Map<String,String> updateIsuse(Article model) {

        Map<String,String> messagesMap = new HashMap<String,String>();

        try {
            log.debug("更新资讯上架下架信息! id=" + model.getId());

            articleService.updateSelective(model);

            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","更新上架下架成功!");
            
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("更新资讯上架下架错误! e = {}", e.getMessage());
        }
        
        return messagesMap;
    }
    
    /**
     * 子类别检索
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/childSelect")
    public Map<String,Object> childSelect(Long parentId) {
        Map<String,Object> messagesMap = new HashMap<String,Object>();
        
        try {
            log.debug("父类别选择时，ajax选择子类别! ");

            ArticleTypes record = new ArticleTypes();
            
            record.setParent_id(parentId);
            
            record.setStatus(1);
            
            List<ArticleTypes> childtypeslist = articleTypeService.findListByWhere(record);
            
            messagesMap.put("childtypeslist", childtypeslist);
            
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","成功!");
            
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("父类别选择时，ajax选择子类别错误! e = {}", e.getMessage());
        }
        
        return messagesMap;
    }

}
