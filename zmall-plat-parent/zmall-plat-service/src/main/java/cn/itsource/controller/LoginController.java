package cn.itsource.controller;

import cn.itsource.domain.User;
import cn.itsource.util.AjaxResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AjaxResult login(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        if ("admin".equals(username)&&"123456".equals(password)){
            return AjaxResult.me().setSuccess(true).setMessage("登录成功").setResultObj(user);
        }
        return AjaxResult.me().setSuccess(false).setMessage("登陆失败，密码错误");
    }
}
