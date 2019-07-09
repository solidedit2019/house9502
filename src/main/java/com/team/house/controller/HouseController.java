package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.service.HouseService;
import com.team.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王建兵
 * @Classname HouseController
 * @Description TODO
 * @Date 2019/7/6 9:40
 * @Created by Administrator
 */
@RestController(value = "houseController2")
@RequestMapping("/admin/")
public class HouseController {
   //以下所有方法全部异回异步
    @Autowired
    private HouseService houseService;

     //查询未审核的功能
    @RequestMapping("getHouseNoPass")
    public Map<String,Object> getHouseNoPass(Page page){
        //调用业务   0表示未审核
       PageInfo<House> pageInfo=houseService.getHouseByIsPass(page,0);
       //创建map
       Map<String,Object> map=new HashMap<>();
       map.put("total",pageInfo.getTotal());
       map.put("rows",pageInfo.getList());
       return map;
    }

    //查询审核的功能
    @RequestMapping("getHouseYesPass")
    public Map<String,Object> getHouseYesPass(Page page){
        //调用业务   0表示未审核
        PageInfo<House> pageInfo=houseService.getHouseByIsPass(page,1);
        //创建map
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }


    //通过审核
    @RequestMapping("houseYes")
    public String houseYes(String id){
        //调用业务   1表示审核
       int temp=houseService.houseIsPass(id,1);
       return "{\"result\":"+temp+"}";
    }


    //不通过审核
    @RequestMapping("houseNo")
    public String houseNo(String id){
        //调用业务   1表示审核
        int temp=houseService.houseIsPass(id,0);
        return "{\"result\":"+temp+"}";
    }
}
