package com.jk.common.base.controller;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 统一错误处理 404 500
 * Created by JK on 2017/2/13.
 */
@Controller
public class GlobalErrorController implements ErrorController {

    private static final Log LOG = LogFactory.get();

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value=ERROR_PATH)
    public String handleError(){
        LOG.error("访问的页面不存在！");
        return "admin/common/404";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
