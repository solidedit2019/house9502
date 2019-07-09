package com.team.house.protal.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 王建兵
 * @Classname TypeController
 * @Description TODO
 * @Date 2019/7/5 8:32
 * @Created by Administrator
 */
@Controller(value = "typeController2")
@RequestMapping("/page/")
//@RestController =  @Controller+ @ResponseBody
public class TypeController {
    @Autowired
    private TypeService typeService;

   @RequestMapping("getType")
   @ResponseBody
   public List<Type>  getType(){
      return  typeService.getAllType();
   }
 }
