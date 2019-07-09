package com.team.house.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.DistrictService;
import com.team.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 王建兵
 * @Classname DistrictServiceImpl
 * @Description TODO
 * @Date 2019/6/25 11:46
 * @Created by Administrator
 */
@Service
public class DistrictServiceImpl implements DistrictService

{

    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo<District> getDistrictByPage(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());

        DistrictExample districtExample=new DistrictExample();
        List<District> list=districtMapper.selectByExample(districtExample);

        return new PageInfo<District>(list);
    }

    @Override
    public int addDistrict(District district) {

        return districtMapper.insertSelective(district);
    }

    @Override
    public District getDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Override
    @Transactional   //表示该方法基于事务执行
    public int deleteDistrict(Integer id)  {
            //删除区域的同时还要删除街道
            //删除街道
            streetMapper.deleteStreetByDid(id);
            //删除区域
            districtMapper.deleteByPrimaryKey(id);
            return 1;
    }

    @Override
    public int delMoreDistrict(List<Integer> ids) {
        return districtMapper.delMoreDistrict(ids);
    }

    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }

    /*编写主函数*/
    public static void main(String[] args)  {
         //创建spring容器
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取对象
        DistrictService districtService=(DistrictService)ctx.getBean("districtServiceImpl");
        //调用方法
        try {
            districtService.deleteDistrict(1013);
        }catch (Exception ex){
            System.out.println("删除失败");
        }

        System.out.println("成功");
    }
}
