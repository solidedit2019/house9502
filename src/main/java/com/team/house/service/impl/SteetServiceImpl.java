package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.entity.StreetExample;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.StreetService;
import com.team.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王建兵
 * @Classname SteetServiceImpl
 * @Description TODO
 * @Date 2019/6/29 10:54
 * @Created by Administrator
 */
@Service
public class SteetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo<Street> getStreetByDid(Integer id, Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());

        StreetExample streetExample=new StreetExample();
        //添加条件
        StreetExample.Criteria criteria=streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(id);  //通过区域编号查询当前街道

        List<Street> list=streetMapper.selectByExample(streetExample);
        return new PageInfo<Street>(list);
    }

    @Override
    public List<Street> getAllStreet(Integer id) {
        StreetExample streetExample=new StreetExample();
        //添加条件
        StreetExample.Criteria criteria=streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(id);
        return streetMapper.selectByExample(streetExample);
    }
}
