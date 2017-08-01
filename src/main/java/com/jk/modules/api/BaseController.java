package com.jk.modules.api;

import com.jk.common.DateEditor;
import com.jk.common.StringEditor;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author
 * Created by JK on 2017/3/10.
 */
@RequestMapping("/api/v1")
public class BaseController {

    protected final transient Log log = LogFactory.get(this.getClass());

    protected final transient String API_URL = "http://localhost/api/v1";

    /**
     * 默认页为1
     */
    protected static final Integer PAGENUM = 1;
    /**
     * 页码大小10
     */
    protected static final Integer PAGESIZE = 10;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new DateEditor(true));
        binder.registerCustomEditor(String.class, "password", new StringEditor(true));
    }

    @ExceptionHandler
    public void exceptionHandler(Exception exception,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

    }

}
