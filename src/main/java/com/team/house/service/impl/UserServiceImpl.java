package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.UsersMapper;
import com.team.house.service.UserService;
import com.team.house.util.MD5Utils;
import com.team.house.util.Page;
import com.team.house.util.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王建兵
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2019/6/29 9:15
 * @Created by Administrator
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public PageInfo<Users> getUserByPage(UserCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());

        UsersExample usersExample=new UsersExample();
        //添加条件
        UsersExample.Criteria criteria=usersExample.createCriteria();
        criteria.andIsadminEqualTo(1);  //管理员

        //添加搜索条件
        if(condition.getName()!=null){
            criteria.andNameLike("%"+condition.getName()+"%");
        }
        if(condition.getTelephone()!=null){
            criteria.andTelephoneLike("%"+condition.getTelephone()+"%");
        }
        if(condition.getStartAge()!=null){
            criteria.andAgeGreaterThanOrEqualTo(condition.getStartAge());
        }
        if(condition.getEndAge()!=null){
            criteria.andAgeLessThanOrEqualTo(condition.getEndAge());
        }


        List<Users> list=usersMapper.selectByExample(usersExample);

        return new PageInfo<Users>(list);
    }

    @Override
    public int checkUserName(String name) {
        //条件查询
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andIsadminEqualTo(0);  //表示普通用户
       //SELECT * FROM users WHERE  NAME='?'
        List<Users> list=usersMapper.selectByExample(usersExample);
        return list.size();  //0没有   1存在
    }

    @Override
    public int addUser(Users user) {
       //设置为注册用户    可以选择在数据库中给此字段设置默认值:0
        user.setIsadmin(0);

        //在数据库保存密码时，不要用明文
        //给用户注册的密码进行md5加密:确保数据的安全性
        String passwordEncrypt= MD5Utils.md5Encrypt(user.getPassword());
        user.setPassword(passwordEncrypt);

        return usersMapper.insertSelective(user);
    }

    @Override
    public Users login(String username, String password) {
        //SELECT * FROM users WHERE NAME=?  AND PASSWORD=?
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //传用户名    传密码
        criteria.andNameEqualTo(username);  //wjb
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password)); // 123
        //执行
        List<Users> list=usersMapper.selectByExample(usersExample);
        if(list.size()==0)
            return null;
        else
            return list.get(0);
    }
}
