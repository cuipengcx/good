package com.jk.modules.sys.controller;


import com.jk.common.Constant;
import com.jk.common.annotation.OperationLog;
import com.jk.common.base.controller.BaseController;
import com.jk.common.security.token.FormToken;
import com.jk.common.util.ShiroUtils;
import com.jk.common.util.WebUtil;
import com.jk.modules.sys.model.User;
import com.jk.modules.sys.service.UserService;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.jk.common.Constant.HEAD_SESSION_STATUS_KEY;
import static com.jk.common.Constant.HEAD_SESSION_STATUS_VALUE;

/**
 * 登录
 * Created by JK on 2017/1/24.
 */
@Controller
@RequestMapping("/admin")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;


    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        log.info("跳转到登录页面！");
        //Ajax登录超时校验,如果超时，进行前台响应提示
        if(WebUtil.isAjaxRequest(request)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader(HEAD_SESSION_STATUS_KEY, HEAD_SESSION_STATUS_VALUE);
            response.setContentType("text/html;charset=utf-8");
        }
        return "admin/login";
    }

    /**
     * 用户登陆
     * 先根据用户名查询出一条用户记录再对比密码是否正确可以防止sql注入
     * @param username  用户名
     * @param password  密码
     * @return
     */
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(String username, String password){
        try {
            //获取当前的Subject
            Subject currentUser = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            log.info("对用户进行登录验证..验证开始! username = {}", username);
            currentUser.login(token);
            //验证是否登录成功
            if(currentUser.isAuthenticated()){
                log.info("对用户进行登录验证..验证通过! username = {}", username);
                return ResponseEntity.ok(Constant.USER_LOGIN_IN);
            }
        }catch (UnknownAccountException e) {  //账号不存在
            log.info("对用户进行登录验证..验证未通过,未知账户! username = {}", username);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constant.USER_NOT_FIND);
        } catch (IncorrectCredentialsException e) {
            log.info("对用户进行登录验证..验证未通过,错误的凭证! username = {}", username);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constant.USER_INVALID);
        } catch (LockedAccountException e) {
            log.info("对用户进行登录验证..验证未通过,账户已锁定! username = {}", username);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constant.USER_HAS_LOCK);
        }catch(ExcessiveAttemptsException eae) {
            log.info("对用户进行登录验证..验证未通过,错误次数过多! username = {}", username);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constant.USER_ERROR_MANY);
        } catch (AuthenticationException e) {
            log.info("对用户进行登录验证..验证未通过,身份验证失败! username = {}" ,username);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constant.USER_INVALID);
        } catch (Exception e) {
            log.error("对用户进行登录验证失败! username = {} e = {}", username, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constant.SYSTEM_ERRORS);
    }

    /**
     * 跳转到修改密码页面
     * @return
     */
    @FormToken(save = true)
    @GetMapping(value = "/password")
    public String password(){
        log.info("跳转到修改密码页面成功!");
        return "admin/password";
    }

    /**
     * 修改密码
     * @return
     */
    @FormToken(remove = true)
    @OperationLog(value = "修改密码")
    @ResponseBody
    @PostMapping(value = "/password")
    public ModelMap updatePassword(String oldPassword, String newPassword){
        ModelMap messagesMap = new ModelMap();
        try {
            //获得当前登陆用户
            User user = ShiroUtils.getUserEntity();

            if(!user.getPassword().equals(SecureUtil.md5().digestHex(oldPassword))){
                log.info("修改密码失败，原始密码不正确!");
                messagesMap.put("status",FAILURE);
                messagesMap.put("message","原始密码不正确!");
                return messagesMap;
            }

            User newUser = new User();
            newUser.setId(user.getId());
            newUser.setPassword(SecureUtil.md5().digestHex(newPassword));
            userService.updateSelective(newUser);

            log.info("修改密码成功!");
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","修改密码成功!");
            return messagesMap;
        } catch (Exception e) {
            log.error("修改密码失败! e = {}", e);
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","修改密码失败!");
            return messagesMap;
        }
    }
}
