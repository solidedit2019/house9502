package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.service.DistrictService;
import com.team.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王建兵
 * @Classname DistrictController
 * @Description TODO
 * @Date 2019/6/25 11:51
 * @Created by Administrator
 */
@Controller
@RequestMapping("/admin/")
public class DistrictController {

     @Autowired
     private DistrictService districtService;

    @RequestMapping("getDistrict")
    @ResponseBody
    public Map<String,Object> getDistrict(Page page){
     PageInfo<District> pageInfo= districtService.getDistrictByPage(page);

     Map<String,Object> objs=new HashMap<>();
     objs.put("total",pageInfo.getTotal());
     objs.put("rows",pageInfo.getList());
     return objs;
    }

    //实现添加
    @RequestMapping("addDistrict")
    @ResponseBody
    public String getDistrict(District district){
        //调用业务
        int temp=districtService.addDistrict(district);
        return "{\"result\":"+temp+"}";
    }


    //获取单行记录
    @RequestMapping("getSingleDistrict")
    @ResponseBody
    public District getDistrict(Integer id){
        return districtService.getDistrict(id);
    }


    //实现修改
    @RequestMapping("upDistrict")
    @ResponseBody
    public String upDistrict(District district){//接收编号，名称
        //调用业务
        int temp=districtService.updateDistrict(district);
        return "{\"result\":"+temp+"}";
    }


    //实现删除
    @RequestMapping("delDistrict")
    @ResponseBody
    public String delDistrict(Integer id)  {//接收编号，名称
        //调用业务
        try {
            districtService.deleteDistrict(id);
            return "{\"result\":1}";
        }catch (Exception e){
            //记录错误信息
            System.out.println("出错啦");
        }
        return "{\"result\":0}";
    }

  // @RequestMapping("delMoreDistrict?id=1&id=2&id=3")  Integer []id
    @RequestMapping("delMoreDistrict")  //?id=1,2,3,4
    @ResponseBody
    public String delMoreDistrict(String id) {//接收编号，名称
        //id=1,2,3,4
        //分割字符串
        String[] arys = id.split(",");
        //转化为List
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arys.length; i++) {
            list.add(Integer.parseInt(arys[i]));
        }
        //调用业务
        int temp = districtService.delMoreDistrict(list);
        return "{\"result\":" + temp + "}";
    }


}
