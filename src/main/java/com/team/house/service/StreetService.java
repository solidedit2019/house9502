package com.team.house.service;


import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.util.Page;

import java.util.List;

/**
 * @author 王建兵
 * @Classname StreetService
 * @Description TODO
 * @Date 2019/6/29 10:52
 * @Created by Administrator
 */
public interface StreetService {

    //查询某区域下的街道
    PageInfo<Street> getStreetByDid(Integer id, Page page);

    //查询某区域下的所有街道
    List<Street> getAllStreet(Integer id);
}
