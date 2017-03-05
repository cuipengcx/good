package com.jk.controller.admin;

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
import com.jk.model.WithDrawCash;
import com.jk.service.WithDrawService;

/**
 * @author yangF
 *         Created by JK on 2017/2/7.
 */
@Controller
@RequestMapping(value="/admin/withdraw", method = { RequestMethod.POST, RequestMethod.GET })
public class WithDrawController extends BaseController {

    private static final String BASE_PATH = "admin/pay/";

    @Resource
    private WithDrawService withdrawService;
    
    @Autowired
    HttpServletRequest request; //这里可以获取到request 
    
    @Autowired
    HttpServletResponse response;
    
    /**
     * 提现列表
     *
     * @param pageNum   当前页码
     * @param title  标题
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/list")
    public String list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            WithDrawCash model, String findproject, String finduser, String findtimestart, String findtimeend, ModelMap modelMap, String ordertype ,String ordervalue) {
        try {
            log.debug("分页查询提现列表! pageNum = {}, username = {}, username = {} ", pageNum, findproject,finduser, findtimestart, findtimeend);
            
            if(ordertype == null){
                ordertype = "0";
                ordervalue = "0";
            }
            
            PageInfo<WithDrawCash> pageInfo = withdrawService.findPage(pageNum, PAGESIZE, findproject,finduser,findtimestart,findtimeend,ordertype, ordervalue);
            log.info("分页查询提现列表成功！ pageInfo = {}", pageInfo);
            modelMap.put("pageInfo", pageInfo);
            modelMap.put("findproject", findproject);
            modelMap.put("finduser", finduser);
            modelMap.put("findtimestart", findtimestart);
            modelMap.put("findtimeend", findtimeend);
            modelMap.put("ordertype", ordertype);
            modelMap.put("ordervalue", ordervalue);
            modelMap.put("pageNum", pageNum);
            
        } catch (Exception e) {
            log.error("提现列表错误! e = {}", e.getMessage());
        }
        return BASE_PATH + "withdraw-list";
    }
    
    /**
     * 提现审核成功
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/success")
    public Map<String,String> withdrawSuccess(WithDrawCash model) {

        Map<String,String> messagesMap = new HashMap<String,String>();

        try {
            log.debug("提现审核成功! id=" + model.getId());
            
            model = withdrawService.findById(model.getId());
            
            model.setStatus(3);
            
            withdrawService.updateSelective(model);

            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","提现审核成功!");
            
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("提现审核错误! e = {}", e.getMessage());
        }
        
        return messagesMap;
    }
    
    /**
     * 提现审核拒绝
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/refuse")
    public Map<String,String> withdrawRefuse(WithDrawCash model, String reason) {

        Map<String,String> messagesMap = new HashMap<String,String>();

        try {
            log.debug("提现审核拒绝! id=" + model.getId());
            
            model = withdrawService.findById(model.getId());
            
            model.setStatus(4);
            model.setRefuse_reason(reason);
            
            withdrawService.updateSelective(model);

            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","提现审核拒绝!");
            
        } catch (Exception e) {
            messagesMap.put("status",FAILURE);
            log.error("提现审核错误! e = {}", e.getMessage());
        }
        
        return messagesMap;
    }
}
