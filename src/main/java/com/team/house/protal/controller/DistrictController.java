package com.team.house.protal.controller;

import com.team.house.entity.District;
import com.team.house.entity.Type;
import com.team.house.service.DistrictService;
import com.team.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 王建兵
 * @Classname TypeController
 * @Description TODO
 * @Date 2019/7/5 8:32
 * @Created by Administrator
 */
@Controller(value = "districtController2")
@RequestMapping("/page/")
//@RestController =  @Controller+ @ResponseBody
public class DistrictController {
    @Autowired
    private DistrictService districtService;

   @RequestMapping("getDistrict")
   @ResponseBody
   public List<District>  getType(){
      return  districtService.getAllDistrict();
   }
 }
