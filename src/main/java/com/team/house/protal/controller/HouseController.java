package com.team.house.protal.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.House;
import com.team.house.entity.Type;
import com.team.house.entity.Users;
import com.team.house.service.DistrictService;
import com.team.house.service.HouseService;
import com.team.house.service.TypeService;
import com.team.house.service.UserService;
import com.team.house.util.HouseCondition;
import com.team.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @author 王建兵
 * @Classname UserController
 * @Description TODO
 * @Date 2019/7/1 8:52
 * @Created by Administrator
 */
@Controller()
@RequestMapping("/page/")
public class HouseController {

    @Autowired
    private DistrictService districtService;
    @Autowired
    private TypeService typeService;

    @Autowired
    private HouseService houseService;

    /*显示发布出租房的页面*/
    @RequestMapping("goFaBu")
    public String  goFaBu(Model model){
        //加载类型
        List<Type> types=typeService.getAllType();
        //加载区域
        List<District> districts=districtService.getAllDistrict();
        //使用model填充数据
        model.addAttribute("types",types);
        model.addAttribute("districts",districts);
        return "fabu";
    }

    //添加出租房
    @RequestMapping("addHouse")
    public String addHouse(HttpSession session,House house,@RequestParam(name = "pfile",required = false) CommonsMultipartFile pfile) throws  Exception{
        //1.实现图片上传：图片在图片服务器 d:/images
        String filename=pfile.getOriginalFilename();  //1.jpg  上传文件名称
        String expname=filename.substring(filename.lastIndexOf("."));  //上传文件的扩展名
        String saveFileName=System.currentTimeMillis()+expname;  //保存文件名称
        String path="d:/images/"+saveFileName;  //保存路径
        File saveFile=new File(path);
        pfile.transferTo(saveFile);  //上传文件
        System.out.println(saveFileName);

        //2.将输入的数据保存到数据库中
        house.setId(System.currentTimeMillis()+"");  //设置编号
        //设置用户id
        Users user=(Users)session.getAttribute("loginInfo");
        house.setUserId(user.getId());
        //设置图片
        house.setPath(saveFileName);

        house.setIsdel(0);  //如果数据有默认值可不设
        house.setIspass(0);  //如果数据有默认值可不设
        //调用业务
        int temp=houseService.addHouse(house);
        if(temp>0){
            return "guanli";
        }else{
            saveFile.delete();  //删除文件
        }
        return "redirect:goFaBu";
    }


    //查询所有用户出租房信息   保需要传Page类中的page属性(页码)
    @RequestMapping("getHouse")
    public String getHouse(Page page,HttpSession session,Model model) throws Exception{
        //调用业务
        //获取用户编号
        Integer uid=((Users)session.getAttribute("loginInfo")).getId();
        PageInfo<House> pageInfo=houseService.getHouseByUser(uid,page);
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }


    @RequestMapping("goUpdate")
    public String goUpdate(String id,Model model) throws Exception{
        //调用业务
        //调用业务查询出租房信息
        House house=houseService.getHouse(id);
        model.addAttribute("house",house);
        return "upfabu";
    }



    @RequestMapping("updateHouse")  //不要忘记传递主键 出租房id
    public String updateHouse(String oldPath,House house,@RequestParam(name = "pfile",required = false) CommonsMultipartFile pfile) throws Exception{
          String filename=pfile.getOriginalFilename();
          if(filename.equals("")){
              //没有选择图片，不用上传新图片，数据不用更新

          }else{
            //上传图片，数据库更新  //删除原图
              //1.实现图片上传：图片在图片服务器 d:/images
              String expname=filename.substring(filename.lastIndexOf("."));  //上传文件的扩展名
              String saveFileName=System.currentTimeMillis()+expname;  //保存文件名称
              String path="d:/images/"+saveFileName;  //保存路径   替换原图
              File saveFile=new File(path);
              pfile.transferTo(saveFile);  //上传文件新图

              //2.删除原有图片
              new File("d://images//"+oldPath).delete();

              //3.设置新图片
              house.setPath(saveFileName);
          }

          //更新数据库
          houseService.updateHouse(house);

          return "redirect:getHouse";
    }


    //删除出租房    ---异步删除
    @RequestMapping("delHouse")
    public String delHouse(String id) throws Exception{
         //调用业务
        houseService.delHouse(id,1);

        return "redirect:getHouse";
    }


    /**
     *显示浏览所有的出租房信息
     * 传页码
     * @return
     */
    @RequestMapping("getBroswerHouse")   //condition包含查询条件及分页的相关属性(page,rows)
    public String  getBroswerHouse(HouseCondition condition, Model model)throws  Exception{
            //调用业务层
            PageInfo<House> pageInfo=houseService.getBorswerHouse(condition);
            model.addAttribute("pageInfo",pageInfo);
            //回显查询条件
            model.addAttribute("condition",condition);
        return  "list";
    }

}
