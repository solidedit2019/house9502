package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.util.Page;
import com.team.house.util.UserCondition;

/**
 * @author 王建兵
 * @Classname UserService
 * @Description TODO
 * @Date 2019/6/29 9:14
 * @Created by Administrator
 */
public interface UserService {

    //查询所有管理员用户
    PageInfo<Users> getUserByPage(UserCondition condition);

    //一个功能一个方法:检查用户名是否存在
    public int checkUserName(String name);

    //注册用户
    public int addUser(Users user);

    //实现登入   通过用户密码去查询数据库
    public Users login(String username,String password);

}
