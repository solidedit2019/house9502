package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.util.HouseCondition;
import com.team.house.util.Page;

import java.util.List;

/**
 * @author 王建兵
 * @Classname HouseService
 * @Description TODO
 * @Date 2019/7/2 10:15
 * @Created by Administrator
 */
public interface HouseService {

    //添加出租房
    public int addHouse(House house);

    //查询用户的出租房信息
    public PageInfo<House> getHouseByUser(Integer id, Page page);

    //查询出租房信息
    public House getHouse(String id);

    //修改出租房信息
    public int updateHouse(House house);


    /**
     * 删除出租房(逻辑删除)
     * @param id
     * @param isdel   如果传0表示正常，1表示逻辑删除
     * @return
     */
    public int delHouse(String id,Integer isdel);


    /**
     *查询未审核|审核的出租房信息
     * @param ispass  0表示正常 未审核  1 表示已审核
     * @return
     */
    public PageInfo<House> getHouseByIsPass(Page page,Integer ispass);


    /**
     * 审核出租房信息
     * @param id  编号
     * @param ispass  0表示未审核  1表示审核
     * @return
     */
    public int houseIsPass(String id,Integer ispass);

    /**
     * 查询所有的出租房
     * @return
     */
    public PageInfo<House> getBorswerHouse(HouseCondition condition);
}
