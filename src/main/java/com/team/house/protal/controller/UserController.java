package com.team.house.protal.controller;

import com.team.house.entity.Users;
import com.team.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 王建兵
 * @Classname UserController
 * @Description TODO
 * @Date 2019/7/1 8:52
 * @Created by Administrator
 */
@Controller("userController2")
@RequestMapping("/page/")
public class UserController {

    @Autowired
    private UserService userService;

    /*检查用户名是否存在*/
    @RequestMapping("checkUname")
    @ResponseBody
    public String  checkUname(String name){
        //调用业务
       int temp=userService.checkUserName(name);
        return "{\"result\":"+temp+"}";
    }


    @RequestMapping("reg")
    public String reg(Users user) throws  Exception{
       //调用业务实现注册
       int temp=userService.addUser(user);
       if(temp>0){
           return "login";   //进入登入页面
       }else{
           return "regs";  //进入注册页面
       }
    }

    //通过用户名密码实现登入ss
    @RequestMapping("loginAction")
    public String loginAction(String name, String password, Model model, HttpSession session) throws  Exception{
        //调用业务实现注册
       Users user=userService.login(name,password);
       if(user==null){
           model.addAttribute("info","用户名和密码不正确");
           return "login";
       }else{
            //只要登入，使用session作用域保存登入的人
            session.setAttribute("loginInfo",user);
            //设置保存的有效时间
            session.setMaxInactiveInterval(6000);  //以秒

           return "redirect:getHouse";   //用户登入后的管理页
       }
    }

}
