package com.team.house.service;


import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.util.Page;

import java.util.List;

/**
 * @author 王建兵
 * @Classname DistrictService
 * @Description TODO
 * @Date 2019/6/25 11:41
 * @Created by Administrator
 */
public interface DistrictService {
    //查询所有区域
    public PageInfo<District> getDistrictByPage(Page page);

     //添加区域  ORM
    public int addDistrict(District district);

    //查询单条记录
    public District getDistrict(Integer id);

    //修改的业务
    public int updateDistrict(District district);

    //实现删除的业务
    public int deleteDistrict(Integer id) ;

    //实现批量删除的业务
    public int delMoreDistrict(List<Integer> ids);

    //查询所有的区域
    public List<District> getAllDistrict();

}
