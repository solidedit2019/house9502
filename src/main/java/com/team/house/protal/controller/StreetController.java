package com.team.house.protal.controller;

import com.team.house.entity.District;
import com.team.house.entity.Street;
import com.team.house.entity.Type;
import com.team.house.service.DistrictService;
import com.team.house.service.StreetService;
import com.team.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 王建兵
 * @Classname UserController
 * @Description TODO
 * @Date 2019/7/1 8:52
 * @Created by Administrator
 */
@Controller(value = "streetController2")
@RequestMapping("/page/")
public class StreetController {

    @Autowired
   private StreetService streetService;


    /*显示发布出租房的页面*/
    @RequestMapping("getStreetByDid")
    @ResponseBody
    public  List<Street>  getStreetByDid(Integer id){
         //查询街道
        List<Street>  streets=streetService.getAllStreet(id);
        return streets;
    }
}
