package com.team.house.service;


import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.util.Page;

import java.util.List;

/**
 * @author 王建兵
 * @Classname TypeService
 * @Description TODO
 * @Date 2019/6/25 11:41
 * @Created by Administrator
 */
public interface TypeService {
    //查询所有区域
    public PageInfo<Type> getTypeByPage(Page page);

     //添加区域  ORM
    public int addType(Type type);

    //查询单条记录
    public Type getType(Integer id);

    //修改的业务
    public int updateType(Type type);

    //实现删除的业务
    public int deleteType(Integer id) ;

    //实现批量删除的业务
    public int delMoreType(List<Integer> ids);

    //查询所有类型
    public List<Type> getAllType();

}
