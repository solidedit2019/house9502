package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.entity.Users;
import com.team.house.service.UserService;
import com.team.house.util.Page;
import com.team.house.util.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王建兵
 * @Classname UserController
 * @Description TODO
 * @Date 2019/6/29 9:19
 * @Created by Administrator
 */
@Controller
@RequestMapping("/admin/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUser")
    @ResponseBody
    public Map<String,Object> getUser(UserCondition condition){

        PageInfo<Users> pageInfo= userService.getUserByPage(condition);

        Map<String,Object> objs=new HashMap<>();
        objs.put("total",pageInfo.getTotal());
        objs.put("rows",pageInfo.getList());
        return objs;
    }
}
