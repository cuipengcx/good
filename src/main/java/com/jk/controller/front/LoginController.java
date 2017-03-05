package com.jk.controller.front;

import com.jk.controller.BaseController;
import com.jk.model.GoodUser;
import com.jk.service.GoodUserService;
import com.jk.util.SessionUtil;
import com.jk.util.ValidateCode;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 注册
 *
 * @author cuiP
 *         Created by JK on 2017/2/27.
 */
@Controller("webLoginController")
@RequestMapping("/front")
public class LoginController extends BaseController {

    private static final String BASE_PATH = "front/";

    @Resource
    private GoodUserService goodUserService;

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping(value = "/login")
    public String toLogin(){
        return BASE_PATH+"login";
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(HttpServletRequest request, String username, String password, String imageCode){
        try {
            log.info("对用户进行登录验证..验证开始! username = {}, password = {}, imageCode = {}", username, password, imageCode);

            String sessionImageCode = (String) WebUtils.getSessionAttribute(request, SessionUtil.SESSION_FRONT_IMAGE_OODE);
            if(!sessionImageCode.equalsIgnoreCase(imageCode)){
                log.info("对用户进行登录验证..验证未通过,验证码错误! username = {}", username);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("验证码错误!");
            }

            GoodUser goodUser = goodUserService.findUserByEmail(username);

            if(null == goodUser){
                log.info("对用户进行登录验证..验证未通过,用户不存在! username = {}", username);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("用户不存在！");
            }
            if(goodUser.getLock()){
                log.info("对用户进行登录验证..验证未通过,账户已锁定! username = {}", username);
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("账户已锁定!");
            }
            if(!goodUser.getPassword().equalsIgnoreCase(SecureUtil.md5().digestHex(password))){
                log.info("对用户进行登录验证..验证未通过,错误的凭证! username = {}", username);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("错误的凭证!");
            }

            //将用户信息放入session
            WebUtils.setSessionAttribute(request, SessionUtil.SESSION_FRONT_USER, goodUser);

            log.info("对用户进行登录验证..验证通过! username = {}", username);
            return ResponseEntity.ok("登录成功！");
        }catch (Exception e) {  //账号不存在
            log.info("对用户进行登录验证..验证失败! username = {}", username);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * 获取验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/imageCode")
    public void getImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ValidateCode validateCode = new ValidateCode(140, 40);

        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Set-Cookie", "name=value; HttpOnly");//设置HttpOnly属性,防止Xss攻击
        response.setDateHeader("Expire", 0);
        WebUtils.setSessionAttribute(request, SessionUtil.SESSION_FRONT_IMAGE_OODE,  validateCode.getCode());
        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            log.info("生成验证码成功，验证码是 imageCode = {}", validateCode.getCode());
            validateCode.write(sos);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != sos){
                sos.close();
            }
        }
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @GetMapping(value = "/register")
    public String toRegister(){
        log.info("跳转到用户注册页面！");
        return BASE_PATH+"register";
    }

    /**
     * 执行用户注册
     * @return
     */
    @PostMapping(value = "/register")
    public String register(){
        log.info("跳转到用户注册页面！");
        return BASE_PATH+"register";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpSession session){
        GoodUser goodUser = (GoodUser) session.getAttribute(SessionUtil.SESSION_FRONT_USER);
        session.invalidate();
        log.info("用户退出成功，email = {}!", goodUser.getEmail());
        return "redirect:/front/login";
    }

}
