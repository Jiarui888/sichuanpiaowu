package cn.scpw.controller;

import cn.scpw.pojo.User;
import cn.scpw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

/**
 * @Author LJR
 * @Date 2019/8/27 12:13
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * 登陆的controller
     * @param user  前端的json封装成user
     * @return  返回json信息
     */
    @RequestMapping(value = "/islogin", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map isLogin(@RequestBody(required = false) User user){

        return userService.isLogin(user);
    }
}
