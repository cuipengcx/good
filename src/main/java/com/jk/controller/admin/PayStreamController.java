package com.jk.controller.admin;

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

import com.github.pagehelper.PageInfo;
import com.jk.controller.BaseController;
import com.jk.model.Article;
import com.jk.model.PayStream;
import com.jk.service.PayStreamService;

/**
 * @author yangF
 *         Created by JK on 2017/2/7.
 */
@Controller
@RequestMapping(value="/admin/paystream", method = { RequestMethod.POST, RequestMethod.GET })
public class PayStreamController extends BaseController {

    private static final String BASE_PATH = "admin/pay/";

    @Resource
    private PayStreamService paystreamService;
    
    @Autowired
    HttpServletRequest request; //这里可以获取到request 
    
    @Autowired
    HttpServletResponse response;
    
    /**
     * 支付流水列表
     *
     * @param pageNum   当前页码
     * @param title  标题
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/list")
    public String list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            PayStream model, String findproject, String finduser, String findtimestart, String findtimeend, ModelMap modelMap, String ordertype ,String ordervalue) {
        try {
            log.debug("分页查询支付流水列表! pageNum = {}, username = {}, username = {} ", pageNum, findproject,finduser, findtimestart, findtimeend);
            
            if(ordertype == null){
                ordertype = "0";
                ordervalue = "0";
            }
            
            PageInfo<PayStream> pageInfo = paystreamService.findPage(pageNum, PAGESIZE, findproject,finduser,findtimestart,findtimeend,ordertype, ordervalue);
            log.info("分页查询支付流水列表成功！ pageInfo = {}", pageInfo);
            modelMap.put("pageInfo", pageInfo);
            modelMap.put("findproject", findproject);
            modelMap.put("finduser", finduser);
            modelMap.put("findtimestart", findtimestart);
            modelMap.put("findtimeend", findtimeend);
            modelMap.put("ordertype", ordertype);
            modelMap.put("ordervalue", ordervalue);
            modelMap.put("pageNum", pageNum);
            
        } catch (Exception e) {
            log.error("支付流水列表错误! e = {}", e.getMessage());
        }
        return BASE_PATH + "paystream-list";
    }
    
}
